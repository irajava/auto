function f() {
    const getBase64FromFile = (file) => {
        return new Promise((resolve, reject) => {
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => resolve(reader.result);
            reader.onerror = error => reject(error);
        });
    };

    const API_URL = 'http://localhost:8080';
    const productionDateInput = document.getElementById("production_date_input");
    const priceInput = document.getElementById("price_input");
    const mileageInput = document.getElementById("mileage_input");
    const numberSeatsInput = document.getElementById("number_seats_input");
    const numberDoorsInput = document.getElementById("number_doors_input");
    const imageInput = document.getElementById("image_input");
    const sendBtn = document.getElementById("send-btn");

    const bodySelect = document.getElementById("body_select");
    const colourSelect = document.getElementById("colour_select");
    const conditioningSelect = document.getElementById("conditioning_select");
    const engineSelect = document.getElementById("engine_select");
    const fuelSelect = document.getElementById("fuel_select");
    const makeSelect = document.getElementById("make_select");
    const modelSelect = document.getElementById("model_select");
    const transmissionSelect = document.getElementById("transmission_select");
    const carsTable = document.getElementById("cars_table");
    document.querySelector('.username-container').innerHTML = window.localStorage.getItem('username');

    const setActionOnUpdateButtonClick = () => {
        for (const el of document.getElementsByClassName('update-btn')) {
            el.addEventListener('click', (e) => {
                const id = e.target.getAttribute('data-id');
                let xhr = new XMLHttpRequest();
                xhr.open('GET', `${API_URL}/car/${id}`);
                xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
                xhr.onload = function () {
                    if (xhr.status !== 200) {
                        console.log('error', xhr);
                    } else {
                        const res = JSON.parse(xhr.response);
                        sendBtn.setAttribute('data-id', id);
                        priceInput.value = res.price;
                        productionDateInput.value = res.productionDate;
                        mileageInput.value = res.mileage;
                        numberSeatsInput.value = res.numberSeats;
                        numberDoorsInput.value = res.numberDoors;
                        if (res.body) {
                            bodySelect.value = res.body.id;
                        }
                        if (res.colour) {
                            colourSelect.value = res.colour.id;
                        }
                        if (res.conditioning) {
                            conditioningSelect.value = res.conditioning.id;
                        }
                        if (res.engine) {
                            engineSelect.value = res.engine.id;
                        }
                        if (res.fuel) {
                            fuelSelect.value = res.fuel.id;
                        }

                        if (res.model) {
                            modelSelect.value = res.model.id;

                        }
                        if (res.make) {
                            makeSelect.value = res.make.id;
                        }
                        if (res.transmission) {
                            transmissionSelect.value = res.transmission.id;
                        }

                    }
                };
                xhr.send();
            });
        }
    };

    const setActionOnDeleteButtonClick = () => {
        for (const el of document.getElementsByClassName('delete-btn')) {
            el.addEventListener('click', (e) => {
                const id = e.target.getAttribute('data-id');

                let xhr = new XMLHttpRequest();
                xhr.open('DELETE', `${API_URL}/car?id=${id}`);
                xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
                xhr.onload = function () {
                    if (xhr.status !== 200) {
                        console.log('error', xhr);
                    } else {
                        e.target.parentElement.parentElement.outerHTML = '';
                    }
                };
                xhr.send();
            });
        }
    };


    const getCarsFromApi = () => {
        let xhr = new XMLHttpRequest();
        xhr.open('GET', `${API_URL}/car`);
        xhr.onload = function () {
            if (xhr.status !== 200) {
                console.log('error', xhr);
            } else {
                carsTable.innerHTML = '';
                for (const car of JSON.parse(xhr.response)) {
                    let row = document.createElement('tr');
                    const imgUrl = car.imageName ? `${API_URL}/foto/${car.imageName}` : 'img/nophoto.jpg';
                    row.innerHTML = `
                        <td>${car.id}</td>
                        <td><img src="${imgUrl}" height="120"></td>
                        <td>${car.model.makeName}</td>
                        <td>${car.model.name}</td>
                        <td>${car.productionDate}</td>
                        <td>${car.mileage}</td>
                        <td>${car.price}</td>
                        <td>${car.bodyType}</td>
                        <td>${car.colourName}</td>
                        <td>${car.conditioningType}</td>
                        <td>${car.transmissionType}</td>
                        <td>${car.fuelType}</td>
                        <td>${car.engineVolume}</td>
                        <td>${car.numberSeats}  </td>
                        <td>${car.numberDoors}</td>
                        <td>
                        <button class="delete-btn" data-id="${car.id}" >Delete</button>
                        <br>
                        <button class="update-btn" data-id="${car.id}">Update</button>
                        </td>

                    `;
                    carsTable.prepend(row);

                }
                setActionOnDeleteButtonClick();
                setActionOnUpdateButtonClick();

            }
        };
        xhr.send();
    };

    let xhr = new XMLHttpRequest();
    xhr.open('GET', `${API_URL}/body`);
    xhr.onload = function () {
        if (xhr.status !== 200) {
            console.log('error', xhr);
        } else {
            console.log('ok', xhr.response);
            bodySelect.innerHTML = '';
            for (const body of JSON.parse(xhr.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', body.id);
                option.innerText = body.type;
                bodySelect.appendChild(option);
            }
        }
    };
    xhr.send();

    let xhr1 = new XMLHttpRequest();
    xhr1.open('GET', `${API_URL}/colour`);
    xhr1.onload = function () {
        if (xhr1.status !== 200) {
            console.log('error', xhr1);
        } else {
            console.log('ok', xhr1.response);
            colourSelect.innerHTML = '';
            for (const colour of JSON.parse(xhr1.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', colour.id);
                option.innerText = colour.name;
                colourSelect.appendChild(option);
            }
        }
    };
    xhr1.send();

    let xhr2 = new XMLHttpRequest();
    xhr2.open('GET', `${API_URL}/conditioning`);
    xhr2.onload = function () {
        if (xhr2.status !== 200) {
            console.log('error', xhr2);
        } else {
            console.log('ok', xhr2.response);
            conditioningSelect.innerHTML = '';
            for (const conditioning of JSON.parse(xhr2.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', conditioning.id);
                option.innerText = conditioning.type;
                conditioningSelect.appendChild(option);
            }
        }
    };
    xhr2.send();

    let xhr3 = new XMLHttpRequest();
    xhr3.open('GET', `${API_URL}/engine`);
    xhr3.onload = function () {
        if (xhr3.status !== 200) {
            console.log('error', xhr3);
        } else {
            console.log('ok', xhr3.response);
            engineSelect.innerHTML = '';
            for (const engine of JSON.parse(xhr3.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', engine.id);
                option.innerText = engine.volume;
                engineSelect.appendChild(option);
            }
        }
    };
    xhr3.send();

    let xhr4 = new XMLHttpRequest();
    xhr4.open('GET', `${API_URL}/fuel`);
    xhr4.onload = function () {
        if (xhr4.status !== 200) {
            console.log('error', xhr4);
        } else {
            console.log('ok', xhr4.response);
            fuelSelect.innerHTML = '';
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
            makeSelect.innerHTML = '';
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
            modelSelect.innerHTML = '';
            for (const model of JSON.parse(xhr6.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', model.id);
                option.innerText = model.name;
                modelSelect.appendChild(option);
            }
        }
    };
    xhr6.send();

    let xhr7 = new XMLHttpRequest();
    xhr7.open('GET', `${API_URL}/transmission`);
    xhr7.onload = function () {
        if (xhr7.status !== 200) {
            console.log('error', xhr7);
        } else {
            console.log('ok', xhr7.response);
            transmissionSelect.innerHTML = '';
            for (const transmission of JSON.parse(xhr7.response)) {
                let option = document.createElement('option');
                option.setAttribute('value', transmission.id);
                option.innerText = transmission.type;
                transmissionSelect.appendChild(option);
            }
        }
    };
    xhr7.send();

    sendBtn.addEventListener('click', () => {
        const car = {
            price: priceInput.value,
            productionDate: productionDateInput.value,
            mileage: mileageInput.value,
            numberSeats: numberSeatsInput.value,
            numberDoors: numberDoorsInput.value,
            bodyId: bodySelect.value,
            colourId: colourSelect.value,
            conditioningId: conditioningSelect.value,
            engineId: engineSelect.value,
            fuelId: fuelSelect.value,
            makeId: makeSelect.value,
            modelId: modelSelect.value,
            transmissionId: transmissionSelect.value,
        };

        // if (person.age < 0 || person.age > 200) {
        //     alert("age");
        //     return;
        // }

        const id = sendBtn.getAttribute('data-id');

        getBase64FromFile(imageInput.files[0])
            .then(image => car.image = image)
            .catch(() => alert("Something went wrong with image, try again"))
            .finally(() => {
                const method = id ? 'PUT' : 'POST';
                let xhr = new XMLHttpRequest();
                xhr.open(method, `${API_URL}/car/${id ? '?id=' + id : ''}`);
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.setRequestHeader('Authorization', `Bearer ${window.localStorage.getItem('autoToken')}`);
                xhr.onload = function () {
                    if (xhr.status !== 200) {
                        console.log('error', xhr);
                        alert("Please,input all fields")
                    } else {
                        console.log('ok', xhr.response);
                        clearAllField();
                        getCarsFromApi();
                    }
                };
                xhr.send(JSON.stringify(car));

            });
    });
    getCarsFromApi();
    const clearAllField = () => {
        priceInput.value = '';
        productionDateInput.value = '';
        mileageInput.value = '';
        numberSeatsInput.value = '';
        numberDoorsInput.value = '';
        bodySelect.value = '';
        colourSelect.value = '';
        conditioningSelect.value = '';
        engineSelect.value = '';
        fuelSelect.value = '';
        makeSelect.value = '';
        modelSelect.value = '';
        transmissionSelect.value = '';
        sendBtn.setAttribute('data-id', '');
    };


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
                    modelSelect.innerHTML = '';
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


}