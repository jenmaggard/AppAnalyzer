#!/bin/bash
for file in /media/sf_megaDownload/EDUCATION2/*
do
   echo "file name $file..."
   fileName=${file##*/}
   dirName=`basename $fileName`
   echo $dirName
#   $dirName=${fileName%.apk}
#   echo " dirname=> $dirName"
   dirPath="/media/sf_megaDownload/EDUCATION_OUT/$dirName"
   dirCmd="mkdir $dirPath" 
   $dirCmd
   txt="python /home/mohsin/Downloads/androguard/cfgAndroFile.py -i $file -o $dirPath/"
   $txt
done

#txt="ls -la"
#$txt
