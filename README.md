
# 后台管理

## how to build code source

In Linux building server:

    git clone git@git.iboxpay.com:cashbox/unified-qrcode-payment.git
    sh build.sh production

*$ sh build.sh*

    Usage: build.sh [p|pre|t|d]

参数说明：
* `p`   或 `production`     : 生产环境
* `pre` 或 `pre_production` : 预发布环境
* `t`   或 `test`           : 测试环境
* `d`   或 `devleopment`    : 开发环境


In Windows:

    cd config
    build.bat
