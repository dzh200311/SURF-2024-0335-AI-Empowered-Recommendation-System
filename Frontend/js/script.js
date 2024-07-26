window.addEventListener('scroll', function () {
    var fadeInElements = document.querySelectorAll('.fade-in-section');

    fadeInElements.forEach(function (element) {
        var position = element.getBoundingClientRect();

        // 检查元素是否在视口中
        if (position.top < window.innerHeight && position.bottom >= 0) {
            element.style.opacity = '1';
            element.style.transform = 'translateY(0)';
        } else {
            element.style.opacity = '0';
            element.style.transform = 'translateY(-20px)';
        }
    });
});





function sendTokenRequest(pageUrl) {
    var token = localStorage.getItem('token'); // 从本地存储中获取 Token
    if (!token) {
        alert('Token not found'); // 如果 Token 不存在，发出警告
        return;
    }

    // 发送带有 Token 的请求
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://120.26.136.194:8000/token/validate', true);
    xhr.setRequestHeader('Authorization', token);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // 请求成功，跳转至页面
                document.write(xhr.responseText);
            } else {
                // 请求失败，处理错误
                alert('Failed to fetch page: ' + xhr.statusText);
            }
        }
    };
    xhr.send();
}

// 切换网站暗黑模式时同时切换地图样式
document.getElementById('toggleDarkMode').addEventListener('click', function() {
    var html = document.documentElement;

    if (html.classList.contains('dark')) {
        html.classList.remove('dark');
        localStorage.setItem('theme', 'light');
        toggleMapTheme();
    } else {
        html.classList.add('dark');
        localStorage.setItem('theme', 'dark');
        toggleMapTheme();
    }
});



document.addEventListener('DOMContentLoaded', (event) => {
    if (localStorage.getItem('theme') === 'dark' || (!('theme' in localStorage) && window.matchMedia('(prefers-color-scheme: dark)').matches)) {
        document.documentElement.classList.add('dark');
    } else {
        document.documentElement.classList.remove('dark');
    }
});
