function toggleDropdown() {
    const dropdown = document.getElementById('dropdownMenu');
    dropdown.classList.toggle('hidden');
}

function closeDropdown() {
    const dropdown = document.getElementById('dropdownMenu');
    dropdown.classList.add('hidden');
}

function logout() {
    // 移除存储的token和登录状态
    localStorage.clear();



    // 可以选择显示登出成功的消息
    alert('You have been logged out.');

    // 重定向到登录页面或主页
    window.location.href = 'index.html'; // 根据你的文件结构调整路径
}