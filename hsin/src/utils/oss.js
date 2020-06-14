
let oss = require('ali-oss');
export function client(data) {
    return oss({
        accessKeyId: 'LTAI4G9tdM6cCXWg1RvnV367',
        accessKeySecret: 'mvSUdL175amOOB7U5pMa8hUxvz0ZOm',
        bucket: 'hsincreation',
        region: 'oss-cn-chengdu',
        secure: true
    })
}



