.class public Lcom/test/maliciousactivity/User;
.super Ljava/lang/Object;
.source "User.java"


# instance fields
.field private pwdObject:Lcom/test/maliciousactivity/Password;

.field private username:Ljava/lang/String;


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1
    .parameter "uname"
    .parameter "password"

    .prologue
    .line 9
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 11
    iput-object p1, p0, Lcom/test/maliciousactivity/User;->username:Ljava/lang/String;

    .line 12
    new-instance v0, Lcom/test/maliciousactivity/Password;

    invoke-direct {v0}, Lcom/test/maliciousactivity/Password;-><init>()V

    .line 13
    .local v0, p:Lcom/test/maliciousactivity/Password;
    invoke-virtual {v0, p2}, Lcom/test/maliciousactivity/Password;->setPassword(Ljava/lang/String;)V

    .line 14
    iput-object v0, p0, Lcom/test/maliciousactivity/User;->pwdObject:Lcom/test/maliciousactivity/Password;

    .line 16
    return-void
.end method


# virtual methods
.method public getPwdObject()Lcom/test/maliciousactivity/Password;
    .locals 1

    .prologue
    .line 24
    iget-object v0, p0, Lcom/test/maliciousactivity/User;->pwdObject:Lcom/test/maliciousactivity/Password;

    return-object v0
.end method

.method public getUsername()Ljava/lang/String;
    .locals 1

    .prologue
    .line 18
    iget-object v0, p0, Lcom/test/maliciousactivity/User;->username:Ljava/lang/String;

    return-object v0
.end method

.method public setPwdObject(Lcom/test/maliciousactivity/Password;)V
    .locals 0
    .parameter "pwdObject"

    .prologue
    .line 27
    iput-object p1, p0, Lcom/test/maliciousactivity/User;->pwdObject:Lcom/test/maliciousactivity/Password;

    .line 28
    return-void
.end method

.method public setUsername(Ljava/lang/String;)V
    .locals 0
    .parameter "username"

    .prologue
    .line 21
    iput-object p1, p0, Lcom/test/maliciousactivity/User;->username:Ljava/lang/String;

    .line 22
    return-void
.end method
