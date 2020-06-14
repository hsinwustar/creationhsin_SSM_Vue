let local = {
    get: function (key) {
        // 存储进去的是字符串所以get的也是字符串，因此要转换成对象
        return JSON.parse(localStorage.getItem(key))
    },
    set: function (key, val) {
        // 只接受字符串，所以val一定要从对象转换成字符串
        localStorage.setItem(key, JSON.stringify(val))
    }
}
export default {
    install: function (vm) {
        // local是获取到的用户名
        vm.prototype.$local = local
    }
}
