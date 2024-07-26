
// 动态加载商品
document.addEventListener('DOMContentLoaded', function() {


    // 商品数据
    const products = [
        {
            id:1,
            name: "Hamburger",
            price: 2,
            description: "Meatloaf",
            imageUrl: "../../images/KFC.jpg",
            specifications: null
        },
        {
            id:2,
            name: "Hamburger 2",
            price: 4,
            description: "Meatloaf",
            imageUrl: "../../images/KFC.jpg",
            specifications: null
        },
        {
            id:3,
            name: "Hamburger 3",
            price: 2,
            description: "Meatloaf",
            imageUrl: "../../images/KFC.jpg",
            specifications: null
        },
        {
            id:4,
            name: "Cheeseburger",
            price: 3,
            description: "Cheese and Meatloaf",
            imageUrl: "../../images/KFC.jpg",
            specifications: ["Small", "Medium", "Large"]
        }
        // ...更多商品
    ];

    const productList = document.getElementById('product-list');

    // 动态加载商品
    products.forEach((product) => {
        const li = document.createElement('li');
        li.className = "inline-flex w-64 flex-col text-center lg:w-auto";

        let buttonHTML = `<a class="select-specifications-button relative flex items-center justify-center rounded-md border border-transparent bg-indigo-500 px-8 py-2 text-sm font-medium text-white hover:bg-indigo-600" data-index="${product.id}">Manager<span class="sr-only">, ${product.name}</span></a>`;

        li.innerHTML = `
            <div class="group relative">
                <div class="aspect-h-1 aspect-w-1 w-full overflow-hidden rounded-md bg-gray-200">
                    <img src="${product.imageUrl}" class="h-full w-full object-cover object-center group-hover:opacity-75">
                </div>
                <div class="mt-6">
                    <h3 class="mt-1 font-semibold text-lg text-gray-900">
                        ${product.name}
                    </h3>
                    <p class="mt-1 text-gray-900">$${product.price}</p>
                    <h5 class="mt-1 font-light text-gray-400">
                        ${product.description}
                    </h5>
                </div>
                <div class="mt-6">
                    ${buttonHTML}
                </div>
            </div>
            <!-- Hidden specifications form -->

            
        `;

        productList.appendChild(li);
    });


    // 事件监听器来显示/隐藏规格表单
    productList.addEventListener('click', function(event) {
        if (event.target.matches('.select-specifications-button')) {
            const index = event.target.dataset.index;
            const form = document.querySelector(`.specifications-form[data-index="${index}"]`);
            form.classList.toggle('hidden');  // 切换显示或隐藏
        }
    });

});


document.addEventListener('DOMContentLoaded', function() {
    fetchCategories();
    fetchNextProductId();
});

//localhost好像要改一下？
function fetchCategories() {
    fetch('http://120.26.136.194:8000/api/product/get_category')
        .then(response => response.json())
        .then(data => {
            const select = document.getElementById('categorySelect');
            data.forEach(category => {
                const option = document.createElement('option');
                option.value = category.id;
                option.textContent = category.name;
                select.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching categories:', error));
}

function fetchNextProductId() {
    fetch('http://120.26.136.194:8000/api/product/get_next_product_id')
        .then(response => response.json())
        .then(data => {
            document.getElementById('productId').value = data;
        })
        .catch(error => console.error('Error fetching product ID:', error));
}

document.getElementById('addProductForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the form from submitting normally

    // Collect form data into an object to be sent
    const formData = {};

    try {
        formData.product_id = parseInt(document.getElementById('productId').value) || null;
        formData.category_id = parseInt(document.getElementById('categorySelect').value) || null;
        formData.price = parseFloat(document.querySelector('[name="price"]').value) || 0;
        formData.discount = parseFloat(document.querySelector('[name="discount"]').value) || 0;


        formData.product_name = document.querySelector('[name="productName"]').value || "";
        formData.description = document.querySelector('[name="description"]').value || "";
        formData.favorite = document.querySelector('[name="favorite"]').checked;
        formData.is_new = document.querySelector('[name="isNew"]').checked;
        formData.discount_starttime = document.querySelector('[name="discountStartTime"]').value || "";
        formData.discount_endtime = document.querySelector('[name="discountEndTime"]').value || "";
    } catch (error) {
        console.error("Error collecting form data: ", error);
    }

    console.log(formData);

    // const imageFile = document.getElementById('productImage').files[0];
    // if (imageFile) {
    //     formData.append('image', imageFile);
    // }

    // POST the form data to the backend
    fetch('http://120.26.136.194:8000/api/product/add_product', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            alert('Product added successfully!');
            toggleModal(); // Assuming there's a function to close the modal
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to add product');
        });
});

