function f() {


    const API_URL = 'http://localhost:8080';
    const carsTable = document.getElementById("cars_table");
    const productionDateFrom = document.getElementById("production_date_from");
    const productionDateTo = document.getElementById("production_date_to");
    const maxPrice = document.getElementById("price_max");
    const minPrice = document.getElementById("price_min");
    const makeSelect = document.getElementById("make_select");
    const modelSelect = document.getElementById("model_select");
    const fuelSelect = document.getElementById("fuel_select");
    const search = document.getElementById("search");
    const pageSize = document.getElementById('page-size');
    const sortedBy = document.getElementById('sortedBy');
    const direction = document.getElementById('direction');
    const pageNumber = document.getElementById('page-number');
    let pages = 0;
    document.querySelector('.username-container').innerHTML = window.localStorage.getItem('username');

    document.getElementById('index').addEventListener('click', (e) => {
        e.preventDefault();
        const xhr = new XMLHttpRequest();
        xhr.open('GET', `${API_URL}/index`);
        xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
        xhr.onload = function () {
            if (xhr.status !== 200) {
                alert("Please register as an administrator")
                // console.log('error', xhr);
            } else {
                document.write(xhr.response);
            }
        };
        xhr.send();
    });

    const setActionOnSearchButtonClick = () => {
        search.addEventListener('click', (e) => {
            let xhr = new XMLHttpRequest();
            xhr.open('GET', `${API_URL}/car/page?paginationRequest.direction=${direction.value}&paginationRequest.field=${sortedBy.value}&paginationRequest.page=${pageNumber.innerHTML}&paginationRequest.size=${pageSize.value}&makeId=${makeSelect.value}&modelId=${modelSelect.value}&fuelId=${fuelSelect.value}
                &productionDateFrom=${productionDateFrom.value}&productionDateTo=${productionDateTo.value}&minPrice=${minPrice.value}&maxPrice=${maxPrice.value} `);

            xhr.onload = function () {
                if (xhr.status !== 200) {
                    console.log('error', xhr);
                } else {
                    console.log('ok', xhr.response);
                    carsTable.innerHTML = '';
                    const res = JSON.parse(xhr.response);
                    for (const car of res.data) {
                        let row = document.createElement('tr');
                        const imgUrl = car.imageName ? `${API_URL}/foto/${car.imageName}` : 'https://www.petescarsales.com/dist/img/nophoto.jpg';
                        row.innerHTML = `
                        <td><img src="${imgUrl}" height="120"></td>
                        <td><b>${car.model.makeName} ${car.model.name}</b> ${car.productionDate}
                        <br> <b> <i style="font-size: 18px "Trebuchet MS", Tahoma, Arial, sans-serif;">${car.price} $</i></b>
                        <br> <i style="font-size: 14px "Trebuchet MS", Tahoma, Arial, sans-serif;">${car.mileage} km</i></td>
                        <td>
                         <a class="detail-btn" href="/item?id=${car.id}">Details</a>
                         <button class="favourite-btn" data-id=${car.id}">&#9734</button>
                        </td>
                    `;
                        carsTable.appendChild(row);

                    }
                    pages = res.totalPages;


                }
            };
            xhr.send();
        });
    };
    setActionOnSearchButtonClick();


    const getCarsFromApi = () => {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', `${API_URL}/car/page?paginationRequest.direction=${direction.value}&paginationRequest.field=${sortedBy.value}&paginationRequest.page=${pageNumber.innerHTML}&paginationRequest.size=${pageSize.value}&makeId=${makeSelect.value}&modelId=${modelSelect.value}&fuelId=${fuelSelect.value}
                &productionDateFrom=${productionDateFrom.value}&productionDateTo=${productionDateTo.value}&minPrice=${minPrice.value}&maxPrice=${maxPrice.value} `);
        xhr.onload = function () {
            var y;
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                carsTable.innerHTML = '';
                const res = JSON.parse(xhr.response);
                for (const car of res.data) {
                    let row = document.createElement('tr');
                    //const year=car.productionDate.getYear.toString();
                    // let y =Date.parse(car.productionDate);
                    //
                    // console.log("year" + y.getYear().toString());
                    const imgUrl = car.imageName ? `${API_URL}/foto/${car.imageName}` : 'https://www.petescarsales.com/dist/img/nophoto.jpg';
                    row.innerHTML = `
                        <td><img src="${imgUrl}" height="120"></td>
                        <td><b>${car.model.makeName} ${car.model.name}</b> ${car.productionDate}
                        <br> <b> <i style="font-size: 18px "Trebuchet MS", Tahoma, Arial, sans-serif;">${car.price} $</i></b>
                        <br> <i style="font-size: 14px "Trebuchet MS", Tahoma, Arial, sans-serif;">${car.mileage} km</i></td>
                        <td>
                         <a class="detail-btn" href="/item?id=${car.id}">Details</a>
                           <!--<button class="favourite-btn" data-id=${car.id}">&#9734</button>-->
                        </td>
                    `;
                    carsTable.appendChild(row);

                }
                pages = res.totalPages;


            }
        };
        xhr.send();
    };

// const setActionOnFavoriteButtonClick = ()=> {
//     for (const el of document.getElementsByClassName('favourite-btn')) {
//             el.addEventListener('click', (e) => {
//                 const id = e.target.getAttribute('data-id');
//                 let xhr = new XMLHttpRequest();
//                 xhr.open('GET', `${API_URL}/car/favorites`);
//                 xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
//                 xhr.onload = function () {
//                     if (xhr.status !== 200) {
//                         console.log('error', xhr);
//                     } else {
//                         'favourite-btn'.setAttribute('data-id', id);
//
//                     }
//                 };
//                 xhr.send();
//         });
//     };
//     }
//     setActionOnFavoriteButtonClick();


    const setActionOnNextButtonClick = () => {
        document.getElementsByClassName('next-btn')[0].addEventListener('click', (e) => {
            // console.log('asd');
            let currentPage = +pageNumber.innerHTML;
            if (currentPage < pages - 1) {
                pageNumber.innerHTML = currentPage + 1;
                getCarsFromApi();
            }
        });
    };
    const setActionOnPreviousButtonClick = () => {
        document.getElementsByClassName('previous-btn')[0].addEventListener('click', (e) => {
            let currentPage = +pageNumber.innerHTML;
            if (currentPage === 0) return;
            pageNumber.innerHTML = currentPage - 1;

            getCarsFromApi();
        });
    };
    const setActionSelectPageSize = () => {
        document.getElementById('page-size').addEventListener('change', (e) => {
            pageSize.value;
            getCarsFromApi();
        });
    };
    const setActionSelectSortedBy = () => {
        document.getElementById('sortedBy').addEventListener('change', (e) => {
            sortedBy.value;
            getCarsFromApi();
        });
    };
    const setActionSelectDirection = () => {
        document.getElementById('direction').addEventListener('change', (e) => {
            direction.value;
            getCarsFromApi();
        });
    };
    setActionSelectPageSize();
    setActionSelectSortedBy();
    setActionSelectDirection();
    setActionOnNextButtonClick();
    setActionOnPreviousButtonClick();
    getCarsFromApi();


    let xhr4 = new XMLHttpRequest();
    xhr4.open('GET', `${API_URL}/fuel`);
    xhr4.onload = function () {
        if (xhr4.status !== 200) {
            console.log('error', xhr4);
        } else {
            console.log('ok', xhr4.response);
            fuelSelect.innerHTML = '<option value="" disabled selected>Select</option>';
            for (const fuel of JSON.parse(xhr4.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', fuel.id);
                option.innerText = fuel.type;
                fuelSelect.appendChild(option);
            }
        }
    };
    xhr4.send();

    let xhr5 = new XMLHttpRequest();
    xhr5.open('GET', `${API_URL}/make`);
    xhr5.onload = function () {
        if (xhr5.status !== 200) {
            console.log('error', xhr5);
        } else {
            console.log('ok', xhr5.response);
            makeSelect.innerHTML = '<option value="" disabled selected>Select</option>';
            for (const make of JSON.parse(xhr5.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', make.id);
                option.innerText = make.name;
                makeSelect.appendChild(option);
            }
        }
    };
    xhr5.send();

    let xhr6 = new XMLHttpRequest();
    xhr6.open('GET', `${API_URL}/model`);
    xhr6.onload = function () {
        if (xhr6.status !== 200) {
            console.log('error', xhr6);
        } else {
            console.log('ok', xhr6.response);
            modelSelect.innerHTML = '<option value="" disabled selected>Select</option>';
            for (const model of JSON.parse(xhr6.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', model.id);
                option.innerText = model.name;
                modelSelect.appendChild(option);
            }
        }
    };
    xhr6.send();

    const setActionSelectMake = () => {
        makeSelect.addEventListener('change', (e) => {
            let xhr = new XMLHttpRequest();
            xhr.open('GET', `${API_URL}/model/byMake?makeId=${makeSelect.value}`);
            // console.log(id);

            xhr.onload = function () {
                if (xhr.status !== 200) {
                    console.log('error', xhr);
                } else {
                    console.log('ok', xhr.response);
                    modelSelect.innerHTML = '<option value="" disabled selected>Select</option>';
                    for (const model of JSON.parse(xhr.response)) {
                        let option = document.createElement('option');
                        option.setAttribute('value', model.id);
                        option.innerText = model.name;
                        modelSelect.appendChild(option);
                    }
                }
            };
            xhr.send();
        });
    };
    setActionSelectMake();


    getCarsFromApi();

}