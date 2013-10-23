# This file is part of Androguard.
#
# Copyright (c) 2012 Geoffroy Gueguen <geoffroy.gueguen@gmail.com>
# All Rights Reserved.
#
# Androguard is free software: you can redistribute it and/or modify
# it under the terms of the GNU Lesser General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# Androguard is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Lesser General Public License for more details.
#
# You should have received a copy of the GNU Lesser General Public License
# along with Androguard.  If not, see <http://www.gnu.org/licenses/>.


class MakeProperties(type):
    def __init__(cls, name, bases, dct):
        def _wrap_set(names, name):
            def fun(self, value):
                for field in names:
                    self.__dict__[field] = (name == field) and value
            return fun

        def _wrap_get(name):
            def fun(self):
                return self.__dict__[name]
            return fun

        super(MakeProperties, cls).__init__(name, bases, dct)
        attrs = []
        prefixes = ('_get_', '_set_')
        for key in dct.keys():
            for i in range(2):
                if key.startswith(prefixes[i]):
                    attrs.append(key[4:])
                    delattr(cls, key)
        for attr in attrs:
            setattr(cls, attr[1:],
                        property(_wrap_get(attr), _wrap_set(attrs, attr)))
        cls._attrs = attrs

    def __call__(cls, *args, **kwds):
        obj = super(MakeProperties, cls).__call__(*args, **kwds)
        for attr in cls._attrs:
            obj.__dict__[attr] = False
        return obj


class LoopType(object):
    __metaclass__ = MakeProperties
    _set_is_pretest = _set_is_posttest = _set_is_endless = None
    _get_is_pretest = _get_is_posttest = _get_is_endless = None

    def copy(self):
        res = LoopType()
        for key, value in self.__dict__.iteritems():
            setattr(res, key, value)
        return res


class NodeType(object):
    __metaclass__ = MakeProperties
    _set_is_cond = _set_is_switch = _set_is_stmt = None
    _get_is_cond = _get_is_switch = _get_is_stmt = None
    _set_is_return = _set_is_throw = None
    _get_is_return = _get_is_throw = None

    def copy(self):
        res = NodeType()
        for key, value in self.__dict__.iteritems():
            setattr(res, key, value)
        return res


class Node(object):
    def __init__(self, name):
        self.name = name
        self.num = 0
        self.follow = {'if': None, 'loop': None, 'switch': None}
        self.looptype = LoopType()
        self.type = NodeType()
        self.interval = None
        self.startloop = False
        self.latch = None
        self.loop_nodes = []

    def copy_from(self, node):
        self.num = node.num
        self.looptype = node.looptype.copy()
        self.interval = node.interval
        self.startloop = node.startloop
        self.type = node.type.copy()
        self.follow = node.follow.copy()
        self.latch = node.latch
        self.loop_nodes = node.loop_nodes

    def update_attribute_with(self, n_map):
        self.latch = n_map.get(self.latch, self.latch)
        for follow_type, value in self.follow.iteritems():
            self.follow[follow_type] = n_map.get(value, value)
        self.loop_nodes = list(set(n_map.get(n, n) for n in self.loop_nodes))

    def get_head(self):
        return self

    def get_end(self):
        return self

    def __repr__(self):
        return str(self)


class Interval(object):
    def __init__(self, head):
        self.name = 'Interval-%s' % head.name
        self.content = set([head])
        self.end = None
        self.head = head
        head.interval = self

    def __contains__(self, item):
        # If the interval contains nodes, check if the item is one of them
        if item in self.content:
            return True
        # If the interval contains intervals, we need to check them
        return any(item in node for node in self.content
                                if isinstance(node, Interval))

    def add_node(self, node):
        if node in self.content:
            return False
        self.content.add(node)
        node.interval = self
        return True

    def compute_end(self, graph):
        for node in self.content:
            for suc in graph.sucs(node):
                if suc not in self.content:
                    self.end = node

    def get_end(self):
        return self.end.get_end()

    def get_head(self):
        return self.head.get_head()

    def __len__(self):
        return len(self.content)

    def __repr__(self):
        return '%s(%s)' % (self.name, self.content)