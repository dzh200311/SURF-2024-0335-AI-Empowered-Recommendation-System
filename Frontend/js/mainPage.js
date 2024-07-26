document.addEventListener('DOMContentLoaded', function() {
    var isLoggedIn = localStorage.getItem('isLoggedIn'); // 假设这是检查登录状态的方法

    var signupButtons = document.getElementById('signup-buttons');
    var userLinks = document.getElementById('user-links');

    if (isLoggedIn) {
        signupButtons.classList.add('hidden'); // 用户登录，隐藏注册按钮
        userLinks.classList.remove('hidden');   // 显示用户链接
    } else {
        signupButtons.classList.remove('hidden'); // 用户未登录，显示注册按钮
        userLinks.classList.add('hidden');       // 隐藏用户链接
    }
});







