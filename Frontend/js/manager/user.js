// 假设的用户数据
const users = [
    { name: "John Doe", title: "Software Engineer", email: "john.doe@example.com", role: "Member" },
    { name: "Jane Smith", title: "UI/UX Designer", email: "jane.smith@example.com", role: "Member" },
    { name: "Michael Johnson", title: "Full-stack Developer", email: "michael.johnson@example.com", role: "Member" },
    { name: "Emily Williams", title: "Product Manager", email: "emily.williams@example.com", role: "Member" },
    { name: "David Brown", title: "Data Scientist", email: "david.brown@example.com", role: "Member" },
    { name: "Sarah Wilson", title: "Front-end Developer", email: "sarah.wilson@example.com", role: "Member" },
    { name: "James Taylor", title: "Back-end Developer", email: "james.taylor@example.com", role: "Member" },
    { name: "Jessica Lee", title: "UI Designer", email: "jessica.lee@example.com", role: "Member" },
    { name: "Matthew Anderson", title: "Software Engineer", email: "matthew.anderson@example.com", role: "Member" },
    { name: "Amanda Martinez", title: "Project Manager", email: "amanda.martinez@example.com", role: "Member" },
    { name: "Daniel Garcia", title: "DevOps Engineer", email: "daniel.garcia@example.com", role: "Member" },
    { name: "Olivia Brown", title: "Software Engineer", email: "olivia.brown@example.com", role: "Member" }
];



function insertUsersIntoTable(userData) {
    // 找到表格的tbody元素
    const tbody = document.querySelector('table tbody');

    // 清空当前的表格行
    tbody.innerHTML = '';

    // 遍历每个用户数据
    userData.forEach(user => {
        // 创建表格行和单元格
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td class="whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-0">${user.name}</td>
            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">${user.title}</td>
            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">${user.email}</td>
            <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">${user.role}</td>
            <td class="relative whitespace-nowrap py-4 pl-3 pr-4 text-right text-sm font-medium sm:pr-0">
                <a href="#" class="text-indigo-600 hover:text-indigo-900">Edit<span class="sr-only">, ${user.name}</span></a>
            </td>
        `;

        // 将行添加到tbody中
        tbody.appendChild(tr);
    });
}

// 假设fetchUsers是异步获取用户数据的函数
async function fetchUsers() {
    // 在这里实现获取数据的逻辑，以下是示意性代码
    // const response = await fetch('/api/users');
    // const data = await response.json();
    // insertUsersIntoTable(data);

    // 使用静态数据的示例
    insertUsersIntoTable(users);
}

// 页面加载完毕后获取并插入用户数据
document.addEventListener('DOMContentLoaded', fetchUsers);
