

var renderer, scene, camera, inside, outside, particle;
var mouseX = 0, mouseY = 0;
var windowHalfX = window.innerWidth / 2;
var windowHalfY = window.innerHeight / 2;

// 初始化Three.js场景
function init() {
    // 创建渲染器
    renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true });

    // 获取特定的容器元素
    const container = document.getElementById('threejs-canvas-container');

    // 设置渲染器大小为容器的大小
    renderer.setSize(container.clientWidth, container.clientHeight);

    // 将渲染器的 DOM 元素添加到容器中
    container.appendChild(renderer.domElement);

    // 创建场景
    scene = new THREE.Scene();

    // 创建相机
    camera = new THREE.PerspectiveCamera(70, window.innerWidth / window.innerHeight, 1, 1000);
    camera.position.z = 500;
    scene.add(camera);

    // 二十面缓冲几何体
    var geom = new THREE.SphereGeometry(7, 10, 10, 0, Math.PI * 2, 0, Math.PI / 2);
    var geom3 = new THREE.CylinderGeometry(115, 115, 12, 14, 14);
    var geom4 = new THREE.CylinderGeometry(116, 116, 16, 14, 14);
    var geom5 = new THREE.CylinderGeometry(70, 70, 15, 14, 14);
    var geom6 = new THREE.CylinderGeometry(70, 70, 15, 14, 14);
    var geom8 = new THREE.CylinderGeometry(70, 70, 15, 14, 14);
    var geom7 = new THREE.SphereGeometry(7, 10, 10, 0, Math.PI * 2, Math.PI / 2, Math.PI);

    var geom2 = new THREE.IcosahedronGeometry(15, 1);

    // 二十面体的材质
    var mat = new THREE.MeshPhongMaterial({
        color: 0xaf6a2e
    });

    var mat2 = new THREE.MeshPhongMaterial({
        color: 0xffffff,
        wireframe: true,
        side: THREE.DoubleSide
    });

    var mat3 = new THREE.MeshPhongMaterial({
        color: 0x4b8300
    });

    var mat4 = new THREE.MeshPhongMaterial({
        color: 0x2b1708
    });

    var mat5 = new THREE.MeshPhongMaterial({
        color: 0xff2c23
    });

    var planet = new THREE.Mesh(geom, mat);
    planet.scale.x = planet.scale.y = planet.scale.z = 16;

    var planet2 = new THREE.Mesh(geom2, mat2);
    planet2.scale.x = planet2.scale.y = planet2.scale.z = 13;

    var planet3 = new THREE.Mesh(geom3, mat3);

    var planet4 = new THREE.Mesh(geom4, mat4);
    var planet5 = new THREE.Mesh(geom5, mat5);
    var planet6 = new THREE.Mesh(geom6, mat5);
    var planet8 = new THREE.Mesh(geom8, mat5);
    var planet7 = new THREE.Mesh(geom7, mat);
    planet7.scale.x = planet7.scale.y = planet7.scale.z = 16;

    planet3.position.y += 1;
    planet4.position.y -= 10;
    planet5.position.y -= 26; planet5.position.x -= 60; planet5.position.z -= 30;
    planet6.position.y -= 26; planet6.position.x += 60; planet6.position.z -= 30;
    planet8.position.y -= 26; planet8.position.z += 60;
    planet7.position.y -= 33;
    planet2.position.y -= 20;

    inside = new THREE.Object3D();
    outside = new THREE.Object3D();

    inside.add(planet);
    inside.add(planet3);
    inside.add(planet4);
    inside.add(planet5);
    inside.add(planet6);
    inside.add(planet7);
    inside.add(planet8);
    scene.add(inside);
    outside.add(planet2);
    scene.add(outside);

    // 环境光
    var ambientLight = new THREE.AmbientLight(0x999999);
    scene.add(ambientLight);

    // 太阳光
    var lights1 = new THREE.DirectionalLight(0xffffff, 1);
    lights1.position.set(1, 0, 0);
    var lights2 = new THREE.DirectionalLight(0xfdf0a5, 1);
    lights2.position.set(0.75, 1, 0.5);
    var lights3 = new THREE.DirectionalLight(0xfdc2a5, 1);
    lights3.position.set(-0.75, -1, 0.5);
    scene.add(lights1);
    scene.add(lights2);
    scene.add(lights3);

    // 环境物体
    particle = new THREE.Object3D();
    scene.add(particle);
    var geometry = new THREE.TetrahedronGeometry(2, 0);
    var material = new THREE.MeshPhongMaterial({
        color: 0xffffff,
        shading: THREE.FlatShading
    });
    for (var i = 0; i < 1000; i++) {
        var mesh = new THREE.Mesh(geometry, material);
        mesh.position.set(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5).normalize();
        mesh.position.multiplyScalar(90 + (Math.random() * 700));
        mesh.rotation.set(Math.random() * 2, Math.random() * 2, Math.random() * 2); //旋转

        particle.add(mesh);
    }

    // 鼠标事件监听器
    document.addEventListener('mousemove', onDocumentMouseMove, false);

    animate();
}

// 鼠标移动事件处理函数
function onDocumentMouseMove(event) {
    mouseX = (event.clientX - windowHalfX) / 2;
    mouseY = (event.clientY - windowHalfY) / 2;
}

// 动画循环
function animate() {
    requestAnimationFrame(animate);

    // 使内部物体旋转
    particle.rotation.x += 0.0010;
    particle.rotation.y -= 0.0040;
    inside.rotation.x -= 0.0020;
    inside.rotation.y -= 0.0030;
    outside.rotation.x -= 0.0010;
    outside.rotation.y += 0.0020;

    // 根据鼠标位置更新相机角度
    camera.position.x += (mouseX - camera.position.x) * 0.05;
    camera.position.y += (-mouseY - camera.position.y) * 0.05;

    camera.lookAt(scene.position);

    renderer.render(scene, camera);
}

// 初始化函数
init();
