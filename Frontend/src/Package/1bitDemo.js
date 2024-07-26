var renderer, scene, inside, inside2, inside3,camera, outside, particle;
var whole;
var a = 3, v = 0, too = 1;
var a2 = 2, v2 = 0, too2 = 1;
var a3 = 2, v3 = 0, too3 = 1;
var mouseX = 0, mouseY = 0;
var windowHalfX = window.innerWidth / 2;
var windowHalfY = window.innerHeight / 2;

// 初始化Three.js场景
function init() {
    // 创建渲染器
    renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true });
    renderer.setSize(window.innerWidth, window.innerHeight);

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
    var boxGeometry1 = new THREE.BoxGeometry(300, 100, 190);
    var boxGeometry2 = new THREE.BoxGeometry(300, 1, 1);
    var boxMaterial = new THREE.MeshStandardMaterial({ color: 0x98711d, roughness: 0.8 }); // 设置粗糙度
    var boxMaterial2 = new THREE.MeshStandardMaterial({ color: 0x544d3b, roughness: 0.8 });
    var box = new THREE.Mesh(boxGeometry1, boxMaterial);
    var box2 = new THREE.Mesh(boxGeometry2, boxMaterial2);
    box2.position.y += 50;//box2.position.y-=50;
    var geom = new THREE.TorusGeometry(120, 10, 100, 5, Math.PI / 3.02);
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
    var mat1 = new THREE.MeshPhongMaterial({ color: 0x7b3a00, transparent: true });
    var mat2 = new THREE.MeshPhongMaterial({ color: 0xff9b26, transparent: true });
    var mat3 = new THREE.MeshPhongMaterial({ color: 0xaa2202, transparent: true });
    var mat4 = new THREE.MeshPhongMaterial({ color: 0x046a07, transparent: true });
    var mat5 = new THREE.MeshPhongMaterial({ color: 0x250f03, transparent: true });

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

    pl1.rotation.x = Math.PI / 2; pl2.rotation.z = Math.PI / 3 * 6;
    pl1.scale.x = pl1.scale.y = pl1.scale.z = 2;
    pl2.rotation.x = Math.PI / 2; pl2.rotation.z = Math.PI / 3;
    pl2.scale.x = pl2.scale.y = pl2.scale.z = 2;
    pl3.rotation.x = Math.PI / 2; pl3.rotation.z = Math.PI / 3 * 2;
    pl3.scale.x = pl3.scale.y = pl3.scale.z = 2;
    pl4.rotation.x = Math.PI / 2; pl4.rotation.z = Math.PI / 3 * 3;
    pl4.scale.x = pl4.scale.y = pl4.scale.z = 2;
    pl5.rotation.x = Math.PI / 2; pl5.rotation.z = Math.PI / 3 * 4;
    pl5.scale.x = pl5.scale.y = pl5.scale.z = 2;
    pl6.rotation.x = Math.PI / 2; pl6.rotation.z = Math.PI / 3 * 5;
    pl6.scale.x = pl6.scale.y = pl6.scale.z = 2;

    pl8.position.x += 20, pl8.position.y += 15, pl8.position.z += 135;
    pl9.position.x -= 73, pl9.position.y += 15, pl9.position.z += 11;
    pl10.position.x += 82, pl10.position.y += 15, pl10.position.z -= 21;
    pl11.position.x -= 25, pl11.position.y += 15, pl11.position.z -= 136;
    pl12.position.x += 105, pl12.position.y += 15, pl12.position.z += 75;
    pl13.position.x -= 76, pl13.position.y += 15, pl13.position.z += 101;
    pl14.position.x += 138, pl14.position.y += 15, pl14.position.z -= 1;
    pl15.position.x += 122, pl15.position.y += 10, pl15.position.z -= 111;
    pl16.position.x -= 125, pl16.position.y += 10, pl16.position.z -= 136;
    pl17.position.x += 105, pl17.position.y += 10, pl17.position.z += 150;
    pl18.position.x -= 2, pl18.position.y += 10, pl18.position.z += 31;
    pl19.position.x -= 148, pl19.position.y += 10, pl19.position.z += 51;
    pl20.rotation.x = Math.PI / 2;
    pl20.position.x -= 15, pl20.position.y += 12, pl20.position.z += 150;
    pl21.rotation.x = Math.PI / 2;
    pl21.position.x += 82, pl21.position.y += 12, pl21.position.z += 31;
    pl22.rotation.x = Math.PI / 2;
    pl22.position.x -= 148, pl22.position.y += 12, pl22.position.z -= 51;

    pl23.rotation.x = Math.PI / 2;
    pl23.position.x += 8, pl23.position.y += 12, pl23.position.z += 65;
    pl24.rotation.x = Math.PI / 2;
    pl24.position.x -= 42, pl24.position.y += 12, pl24.position.z -= 91;
    pl25.rotation.x = Math.PI / 2;
    pl25.position.x += 148, pl25.position.y += 12, pl25.position.z -= 51;

    // topTorus.rotation.x = Math.PI / 2; // 将圆环绕 x 轴旋转 90 度
    // bottomTorus.rotation.x = Math.PI / 2; // 将圆环绕 x 轴旋转 90 度
    // bottomTorus.position.y -= 1;

    // whole = new THREE.Object3D();
    inside = new THREE.Object3D();
    inside2 = new THREE.Object3D();

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
    inside2.add(box);
    inside2.add(box2);
    // inside.add(pl9);
    // inside.add(planet3);
    // inside.add(planet4);
    // inside.add(planet5);
    // inside.add(planet6);
    // inside.add(planet7);
    // inside.add(planet8);
    inside2.position.y = 230;
    inside.scale.x = inside.scale.y = inside.scale.z = 0.3;
    inside.position.y = 280;inside.position.x =-40;inside.position.z-=40;
    scene.add(inside);
    //  scene2.add(inside2);
    //  outside.add(planet2);
    scene.add(inside2);

    var tgeom = new THREE.TorusGeometry(100, 40, 100, 100);
    var tgeom2 = new THREE.TorusGeometry(100, 40, 70, 100);
    var tgeom3 = new THREE.CylinderGeometry(3, 3, 12, 14, 14);
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
    var tmat1 = new THREE.MeshPhongMaterial({ color: 0xe94d90,transparent: true });
    var tmat2 = new THREE.MeshPhongMaterial({ color: 0xfdc2a5,transparent: true });
    var tmat3 = new THREE.MeshPhongMaterial({ color: 0xffe600,transparent: true });
    var tmat4 = new THREE.MeshPhongMaterial({ color: 0xa5befd,transparent: true });
    var tmat5 = new THREE.MeshPhongMaterial({ color: 0xe94a80,transparent: true });

    var tpl1 = new THREE.Mesh(tgeom3, tmat3);
    var tpl2 = new THREE.Mesh(tgeom3, tmat3);
    var tpl3 = new THREE.Mesh(tgeom3, tmat3);
    var tpl4 = new THREE.Mesh(tgeom3, tmat4);
    var tpl5 = new THREE.Mesh(tgeom3, tmat4);
    var tpl6 = new THREE.Mesh(tgeom3, tmat4);
    var tpl7 = new THREE.Mesh(tgeom3, tmat5);
    var tpl8 = new THREE.Mesh(tgeom3, tmat5);
    var tpl9 = new THREE.Mesh(tgeom3, tmat5);
    var topTorus = new THREE.Mesh(tgeom, tmat1);
    var bottomTorus = new THREE.Mesh(tgeom2, tmat2);

    tpl1.rotation.x= Math.PI / 2;tpl1.rotation.z = Math.PI / 2;
    tpl1.position.y += 41;tpl1.position.x += 100;

    tpl2.rotation.x= Math.PI / 2;tpl2.rotation.z = Math.PI / 2;
    tpl2.position.y += 41;tpl2.position.z += 100;

    tpl3.rotation.x= Math.PI / 2;tpl3.rotation.z = Math.PI / 3;
    tpl3.position.y += 41;tpl3.position.z -= 100*1.414/2;tpl3.position.x -= 100*1.414/2;

    tpl4.rotation.x= Math.PI / 2;tpl4.rotation.z = Math.PI /1.5;
    tpl4.position.y += 35;tpl4.position.z += 160*1.414/2;tpl4.position.x -= 100*1.414/2;

    tpl5.rotation.x= Math.PI / 2;tpl5.rotation.z = Math.PI /1.2;
    tpl5.position.y += 35;tpl5.position.z -= 40*1.414/2;tpl5.position.x += 100*1.414/2;

    tpl6.rotation.x= Math.PI / 2;tpl6.rotation.z = Math.PI /2;
    tpl6.position.y += 41;tpl6.position.x -= 100;

    tpl7.rotation.x= Math.PI / 2;tpl7.rotation.z = Math.PI /3;
    tpl7.position.y += 35;tpl7.position.z += 160*1.414/2;tpl7.position.x += 100*1.414/2;

    tpl8.rotation.x= Math.PI / 2;tpl8.rotation.z = Math.PI /1.5;
    tpl8.position.y += 41;tpl8.position.z -= 90;tpl8.position.x +=15;

    tpl9.rotation.x= Math.PI / 2;tpl9.rotation.z = Math.PI/1.5 ;
    tpl9.position.y += 37;tpl9.position.z += 90;tpl9.position.x -=95;

    topTorus.rotation.x = Math.PI / 2; // 将圆环绕 x 轴旋转 90 度
    bottomTorus.rotation.x = Math.PI / 2; // 将圆环绕 x 轴旋转 90 度
    bottomTorus.position.y -= 1;


    inside3 = new THREE.Object3D();

    inside3.add(topTorus);
    inside3.add(bottomTorus);
    inside3.add(tpl1);
    inside3.add(tpl2);
    inside3.add(tpl3);
    inside3.add(tpl4);
    inside3.add(tpl5);
    inside3.add(tpl6);
    inside3.add(tpl7);
    inside3.add(tpl8);
    inside3.add(tpl9);
    inside3.position.y=330;inside3.position.x=80;inside3.position.z+=40;
    inside3.scale.x=inside3.scale.y=inside3.scale.z=0.3;
    scene.add(inside3);

    var hgeom = new THREE.SphereGeometry(7, 10, 10, 0, Math.PI * 2, 0, Math.PI / 2);
    var hgeom3 = new THREE.CylinderGeometry(115, 115, 12, 14, 14);
    var hgeom4 = new THREE.CylinderGeometry(116, 116, 16, 14, 14);
    var hgeom5 = new THREE.CylinderGeometry(70, 70, 15, 14, 14);
    var hgeom6 = new THREE.CylinderGeometry(70, 70, 15, 14, 14);
    var hgeom8 = new THREE.CylinderGeometry(70, 70, 15, 14, 14);
    var hgeom7 = new THREE.SphereGeometry(7, 10, 10, 0, Math.PI * 2, Math.PI / 2, Math.PI);


    // 二十面体的材质
    var hmat = new THREE.MeshPhongMaterial({
        color: 0xaf6a2e
    });


    var hmat3 = new THREE.MeshPhongMaterial({
        color: 0x4b8300
    });

    var hmat4 = new THREE.MeshPhongMaterial({
        color: 0x2b1708
    });

    var hmat5 = new THREE.MeshPhongMaterial({
        color: 0xff2c23
    });

    var hplanet = new THREE.Mesh(hgeom, hmat);
    hplanet.scale.x = hplanet.scale.y = hplanet.scale.z = 16;

    var hplanet3 = new THREE.Mesh(hgeom3, hmat3);

    var hplanet4 = new THREE.Mesh(hgeom4, hmat4);
    var hplanet5 = new THREE.Mesh(hgeom5, hmat5);
    var hplanet6 = new THREE.Mesh(hgeom6, hmat5);
    var hplanet8 = new THREE.Mesh(hgeom8, hmat5);
    var hplanet7 = new THREE.Mesh(hgeom7, hmat);
    hplanet7.scale.x = hplanet7.scale.y = hplanet7.scale.z = 16;

    hplanet3.position.y += 1;
    hplanet4.position.y -= 10;
    hplanet5.position.y -= 26; hplanet5.position.x -= 60; hplanet5.position.z -= 30;
    hplanet6.position.y -= 26; hplanet6.position.x += 60; hplanet6.position.z -= 30;
    hplanet8.position.y -= 26; hplanet8.position.z += 60;
    hplanet7.position.y -= 33;

    inside4 = new THREE.Object3D();

    inside4.add(hplanet);
    inside4.add(hplanet3);
    inside4.add(hplanet4);
    inside4.add(hplanet5);
    inside4.add(hplanet6);
    inside4.add(hplanet7);
    inside4.add(hplanet8);
    inside4.scale.x=0.3;inside4.scale.y=0.3;inside4.scale.z=0.3;
    inside4.position.y=310;inside4.position.x=-20;inside4.position.z+=50;
    //whole.add(inside);whole.add(inside2);whole.add(inside3);whole.add(inside4);
    scene.add(inside4);
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
    //   whole.rotation.y-=0.0020;
    // 
    //  outside.rotation.x -= 0.0010;
    //   outside.rotation.y += 0.0020;

    // 使内部物体做自由落体运动
    if (inside2.position.y > -120)
        inside2.position.y -= v * too;
    inside.position.y -= v * too,// 自由落体速度
        v += a * too;
    inside3.position.y -= v2 * too2,// 自由落体速度
        v2 += a2 * too2;
    inside4.position.y -= v3 * too3,// 自由落体速度
        v3 += a3 * too3;
    //inside.position.y-=v*too,v+=a*too;
    if (inside.position.y <= -70) { // 如果物体掉落到一定位置
        if (v >= 3 && too == 1) {
            v /=2;
            too = -1;
        }
        else too = 2, inside.position.y = -70; // 将其停在页面底端上方
    }
    else if (v <= 0 && too == -1) { // 如果物体掉落到一定位置
        too= 1;
    }

    if (inside3.position.y <= -70) { // 如果物体掉落到一定位置
        if (v2 >= 3 && too2 == 1) {
            v2 /= 1.9;
            too2 = -1;
        }
        else too2 = 2, inside3.position.y = -70; // 将其停在页面底端上方
    }
    else if (v2 <= 0 && too2 == -1) { // 如果物体掉落到一定位置
        too2= 1;
    }

    if (inside4.position.y <= -45) { // 如果物体掉落到一定位置
        if (v3 >= 3 && too3 == 1) {
            v3 /= 1.5;
            too3 = -1;
        }
        else too3 = 2, inside4.position.y = -45; // 将其停在页面底端上方
    }
    else if (v3 <= 0 && too3 == -1) { // 如果物体掉落到一定位置
        too3= 1;
    }
    // 根据鼠标位置更新相机角度
    camera.position.x += (mouseX - camera.position.x) * 0.05;
    // camera.position.y += (-mouseY - camera.position.y) * 0.05;

    camera.lookAt(scene.position);

    renderer.render(scene, camera);
}

// 初始化函数
init();