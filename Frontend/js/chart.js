// 饼图1
var ctx1 = document.getElementById('pieChart1').getContext('2d');
var pieChart1 = new Chart(ctx1, {
    type: 'pie',
    data: {
        labels: ['Appetizers', 'Main Courses', 'Desserts'],
        datasets: [{
            label: 'Daily Sales by Dish Type',
            data: [150, 200, 100], // 假设数据，实际数据应从后台系统获取
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)'
            ],
            hoverOffset: 4
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                display: true,
                position: 'top'
            }
        }
    }
});

// 饼图2
var ctx2 = document.getElementById('pieChart2').getContext('2d');
var pieChart2 = new Chart(ctx2, {
    type: 'pie',
    data: {
        labels: ['Pizza', 'Burger', 'Salad'],
        datasets: [{
            label: 'Sales',
            data: [300, 250, 200], // 模拟销售数据
            backgroundColor: ['rgb(255, 99, 132)', 'rgb(54, 162, 235)', 'rgb(255, 205, 86)'],
            hoverOffset: 4
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false
    }
});


// 折线图
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
            {
                label: 'Sales',
                data: [10, 10, 3, 5, 2, 3, 9],
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgb(41,80,158)',
                borderWidth: 5
            },
            {
                label: 'Expenses',
                data: [8, 5, 6, 2, 5, 8, 4],
                backgroundColor: 'rgba(255, 159, 64, 0.2)',
                borderColor: 'rgb(255, 159, 64)',
                borderWidth: 5
            },
            {
                label: 'Profit',
                data: [2, 5, -3, 3, -3, -5, 5],
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgb(75, 192, 192)',
                borderWidth: 5
            }
        ]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

//柱状图
document.addEventListener('DOMContentLoaded', function () {
    var ctx = document.getElementById('barChart').getContext('2d');
    var barChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June'],
            datasets: [{
                label: 'Visitors',
                data: [65, 59, 80, 81, 56, 55, 40],
                backgroundColor: [
                    'rgb(255,99,132)',
                    'rgb(54,162,235)',
                    'rgb(255,206,86)',
                    'rgb(75,192,192)',
                    'rgb(153,102,255)',
                    'rgb(255,159,64)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
});

//雷达图
document.addEventListener('DOMContentLoaded', function () {
    var ctx = document.getElementById('radarChart').getContext('2d');
    var radarChart = new Chart(ctx, {
        type: 'radar',
        data: {
            labels: ['Eating', 'Drinking', 'Sleeping', 'Designing', 'Coding', 'Cycling', 'Running'],
            datasets: [{
                label: 'My First Dataset',
                data: [65, 59, 90, 81, 56, 55, 40],
                fill: true,
                backgroundColor: 'rgba(255, 99, 132, 0.2)',
                borderColor: 'rgb(255, 99, 132)',
                pointBackgroundColor: 'rgb(255, 99, 132)',
                pointBorderColor: '#fff',
                pointHoverBackgroundColor: '#fff',
                pointHoverBorderColor: 'rgb(255, 99, 132)'
            }, {
                label: 'My Second Dataset',
                data: [28, 48, 40, 19, 96, 27, 100],
                fill: true,
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgb(54, 162, 235)',
                pointBackgroundColor: 'rgb(54, 162, 235)',
                pointBorderColor: '#fff',
                pointHoverBackgroundColor: '#fff',
                pointHoverBorderColor: 'rgb(54, 162, 235)'
            }]
        },
        options: {
            elements: {
                line: {
                    borderWidth: 3
                }
            }
        }
    });
});

// LineStack
document.addEventListener('DOMContentLoaded', function() {
    var ctx = document.getElementById('AreaChart').getContext('2d');
    var myAreaChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
            datasets: [{
                label: 'My First dataset',
                backgroundColor: 'rgba(255, 99, 132, 0.5)',
                data: [65, 59, 80, 81, 56, 55, 40],
                fill: 'origin', // 0: fill to 'origin'
            }, {
                label: 'My Second dataset',
                fill: '-1', // 1: fill to dataset 0
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                data: [28, 48, 40, 19, 86, 27, 90],
            }, {
                label: 'My Third dataset',
                fill: 1, // 2: fill to dataset 1
                backgroundColor: 'rgba(255, 206, 86, 0.5)',
                data: [-10, 30, -20, 0, 25, 44, -30],
            }, {
                label: 'My Fourth dataset',
                fill: '-1', // 3: fill to dataset 2
                backgroundColor: 'rgba(75, 192, 192, 0.5)',
                data: [45, 25, 16, 36, 67, 18, 76],
            }]
        },
        options: {
            scales: {
                y: {
                    stacked: true
                }
            }
        }
    });
});