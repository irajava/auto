function f() {
    const searchParams = new URLSearchParams(window.location.search);
    const setDataFromApi = (data) => {
        // $('.title').html(data.model.makeName,);
        $('.container').append(`
        <img class="item-photo" src="http://localhost:8080/foto/${data.imageName}">
        <div class="item-description">
            <p class="item-price"><b>Make:</b><i> ${data.model.makeName}</i></p>
            <p class="item-price"><b>Model:</b><i> ${data.model.name}</i></p>
            <p class="item-price"><b>Production date:</b><i> ${data.productionDate}</i></p>
            <p class="item-price"><b>Price: </b><i>${data.price}$</i></p>
            <br>
            <p class="item-mileage"><b>Mileage: </b><i>${data.mileage}km</i></p>
            <p class="item-price"><b>Type of transmission:</b><i> ${data.transmissionType}</i></p>
            <p class="item-price"><b>Type of fuel:</b><i> ${data.fuelType}</i></p>
            <p class="item-price"><b>Engine volume: </b><i>${data.engineVolume}</i></p>
            <p class="item-price"><b>Type of body:</b><i> ${data.bodyType}</i></p>
            <p class="item-price"><b>Type of conditioning: </b><i>${data.conditioningType}</i></p>
            <p class="item-price"><b>Colour: </b><i>${data.colourName}</i></p>
            <p class="item-price"><b>Number seats: </b><i>${data.numberSeats}</i></p>
            <p class="item-price"><b>Number doors:</b><i> ${data.numberDoors}</i></p>


        </div>

        `);
    };
    $.ajax({
        url: `http://localhost:8080/car/byId?id=${searchParams.get('id')}`,
        type: 'get',
        success: (res) => {
            setDataFromApi(res)
        },
        error: console.log
    })
}