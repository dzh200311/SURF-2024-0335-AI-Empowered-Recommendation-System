

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
    var geom = new THREE.TorusGeometry(120, 10, 100, 5,Math.PI/3.02);
    var geom2 = new THREE.CylinderGeometry(240, 240, 1, 14, 14);
    var geom3 = new THREE.CylinderGeometry(33, 33, 3, 14, 14);
    var geom4 = new THREE.CylinderGeometry(13, 13, 1, 14, 14);
    var geom5 = new THREE.TorusGeometry(11, 2, 100, 5);

    // var geom3 = new THREE.CylinderGeometry(115, 115, 12, 14, 14);
    // var geom4 = new THREE.CylinderGeometry(116, 116, 16, 14, 14);
    // var geom5 = new THREE.CylinderGeometry(70, 70, 15, 14, 14);
    // var geom6 = new THREE.CylinderGeometry(70, 70, 15, 14, 14);
    // var geom8 = new THREE.CylinderGeometry(70, 70, 15, 14, 14);
    // var geom7 = new THREE.SphereGeometry(7, 10, 10, 0, Math.PI * 2, Math.PI / 2, Math.PI);

    // var geom2 = new THREE.IcosahedronGeometry(15, 1);
    // 二十面体的材质
    // var mat = new THREE.MeshPhongMaterial({
    //     color: 0xe94d90,
    //     transparent: true,
    // //    opacity: 0.5
    // });
    var mat1 = new THREE.MeshPhongMaterial({ color: 0x7b3a00,transparent: true });
    var mat2 = new THREE.MeshPhongMaterial({ color: 0xff9b26,transparent: true });
    var mat3 = new THREE.MeshPhongMaterial({ color: 0xaa2202,transparent: true });
    var mat4 = new THREE.MeshPhongMaterial({ color: 0x046a07,transparent: true });
    var mat5 = new THREE.MeshPhongMaterial({ color: 0x250f03,transparent: true });

    var pl1 = new THREE.Mesh(geom, mat1);
    var pl2 = new THREE.Mesh(geom, mat1);
    var pl3 = new THREE.Mesh(geom, mat1);
    var pl4 = new THREE.Mesh(geom, mat1);
    var pl5 = new THREE.Mesh(geom, mat1);
    var pl6 = new THREE.Mesh(geom, mat1);
    var pl7 = new THREE.Mesh(geom2, mat2);
    var pl8 = new THREE.Mesh(geom3, mat3);
    var pl9 = new THREE.Mesh(geom3, mat3);
    var pl10 = new THREE.Mesh(geom3, mat3);
    var pl11 = new THREE.Mesh(geom3, mat3);
    var pl12 = new THREE.Mesh(geom3, mat3);
    var pl13 = new THREE.Mesh(geom3, mat3);
    var pl14 = new THREE.Mesh(geom3, mat3);
    var pl15 = new THREE.Mesh(geom4, mat3);
    var pl16 = new THREE.Mesh(geom4, mat3);
    var pl17 = new THREE.Mesh(geom4, mat3);
    var pl18 = new THREE.Mesh(geom4, mat3);
    var pl19 = new THREE.Mesh(geom4, mat3);
    var pl20 = new THREE.Mesh(geom5, mat4);
    var pl21 = new THREE.Mesh(geom5, mat4);
    var pl22 = new THREE.Mesh(geom5, mat4);
    var pl23 = new THREE.Mesh(geom5, mat5);
    var pl24 = new THREE.Mesh(geom5, mat5);
    var pl25 = new THREE.Mesh(geom5, mat5);
    //  var topTorus = new THREE.Mesh(geom, mat1);
    // var bottomTorus = new THREE.Mesh(geom2, mat2);

    pl1.rotation.x= Math.PI / 2;pl2.rotation.z= Math.PI / 3*6;
    pl1.scale.x=pl1.scale.y=pl1.scale.z=2;
    pl2.rotation.x= Math.PI / 2;pl2.rotation.z= Math.PI / 3;
    pl2.scale.x=pl2.scale.y=pl2.scale.z=2;
    pl3.rotation.x= Math.PI / 2;pl3.rotation.z= Math.PI / 3*2;
    pl3.scale.x=pl3.scale.y=pl3.scale.z=2;
    pl4.rotation.x= Math.PI / 2;pl4.rotation.z= Math.PI / 3*3;
    pl4.scale.x=pl4.scale.y=pl4.scale.z=2;
    pl5.rotation.x= Math.PI / 2;pl5.rotation.z= Math.PI / 3*4;
    pl5.scale.x=pl5.scale.y=pl5.scale.z=2;
    pl6.rotation.x= Math.PI / 2;pl6.rotation.z= Math.PI / 3*5;
    pl6.scale.x=pl6.scale.y=pl6.scale.z=2;

    pl8.position.x+=20,pl8.position.y+=15,pl8.position.z+=135;
    pl9.position.x-=73,pl9.position.y+=15,pl9.position.z+=11;
    pl10.position.x+=82,pl10.position.y+=15,pl10.position.z-=21;
    pl11.position.x-=25,pl11.position.y+=15,pl11.position.z-=136;
    pl12.position.x+=105,pl12.position.y+=15,pl12.position.z+=75;
    pl13.position.x-=76,pl13.position.y+=15,pl13.position.z+=101;
    pl14.position.x+=138,pl14.position.y+=15,pl14.position.z-=1;
    pl15.position.x+=122,pl15.position.y+=10,pl15.position.z-=111;
    pl16.position.x-=125,pl16.position.y+=10,pl16.position.z-=136;
    pl17.position.x+=105,pl17.position.y+=10,pl17.position.z+=150;
    pl18.position.x-=2,pl18.position.y+=10,pl18.position.z+=31;
    pl19.position.x-=148,pl19.position.y+=10,pl19.position.z+=51;
    pl20.rotation.x= Math.PI / 2;
    pl20.position.x-=15,pl20.position.y+=12,pl20.position.z+=150;
    pl21.rotation.x= Math.PI / 2;
    pl21.position.x+=82,pl21.position.y+=12,pl21.position.z+=31;
    pl22.rotation.x= Math.PI / 2;
    pl22.position.x-=148,pl22.position.y+=12,pl22.position.z-=51;

    pl23.rotation.x= Math.PI / 2;
    pl23.position.x+=8,pl23.position.y+=12,pl23.position.z+=65;
    pl24.rotation.x= Math.PI / 2;
    pl24.position.x-=42,pl24.position.y+=12,pl24.position.z-=91;
    pl25.rotation.x= Math.PI / 2;
    pl25.position.x+=148,pl25.position.y+=12,pl25.position.z-=51;

    // topTorus.rotation.x = Math.PI / 2; // 将圆环绕 x 轴旋转 90 度
    // bottomTorus.rotation.x = Math.PI / 2; // 将圆环绕 x 轴旋转 90 度
    // bottomTorus.position.y -= 1;


    inside = new THREE.Object3D();
    //  outside = new THREE.Object3D();

    // inside.add(topTorus);
    //inside.add(bottomTorus);
    inside.add(pl1);
    inside.add(pl2);
    inside.add(pl3);
    inside.add(pl4);
    inside.add(pl5);
    inside.add(pl6);
    inside.add(pl7);
    inside.add(pl8);
    inside.add(pl9);
    inside.add(pl10);
    inside.add(pl11);
    inside.add(pl12);
    inside.add(pl13);
    inside.add(pl14);
    inside.add(pl15);
    inside.add(pl16);
    inside.add(pl17);
    inside.add(pl18);
    inside.add(pl19);
    inside.add(pl20);
    inside.add(pl21);
    inside.add(pl22);
    inside.add(pl23);
    inside.add(pl24);
    inside.add(pl25);
    // inside.add(pl9);
    // inside.add(planet3);
    // inside.add(planet4);
    // inside.add(planet5);
    // inside.add(planet6);
    // inside.add(planet7);
    // inside.add(planet8);
    //inside.position.y=200;
    inside.rotation.x=Math.PI/2;
    scene.add(inside);
    //  outside.add(planet2);
    //   scene.add(outside);

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
   // particle.rotation.x += 0.0010;
    particle.rotation.y -= 0.0040;
   // inside.rotation.x -= 0.0020;
    inside.rotation.y -= 0.0030;
    //outside.rotation.x -= 0.0010;
    //outside.rotation.y += 0.0020;

    // 根据鼠标位置更新相机角度
    camera.position.x += (mouseX - camera.position.x) * 0.05;
    camera.position.y += (-mouseY - camera.position.y) * 0.05;

    camera.lookAt(scene.position);

    renderer.render(scene, camera);
}

// 初始化函数
init();
