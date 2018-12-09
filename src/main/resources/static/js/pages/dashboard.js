let listCountry = [];


let planet = planetaryjs.planet();

let countryColors = ['red', 'yellow', 'white', 'orange', 'green', 'cyan', 'pink'];
let regions = ['Africa', 'Americas', 'Asia', 'Europe', 'Oceania'];
let language = ['es', 'fr', 'en', 'ar', 'ru', 'ko'];


function readByRegion() {
    regions.forEach((val, index) => {
        setTimeout(() => {
            let eventSource = new EventSource("country/all/region/" + val);

            eventSource.onmessage = (message) => {
                console.info("Debut du Process 'receiveData'");
                console.info("Raw Data : " + message.data);
                let data = JSON.parse(message.data);
                addPing(data);
            };

            eventSource.onopen = (e) => {
                console.info("Debut du Process 'countrySourceIsOpen'");
                console.info("Data : " + e);

                var myStack = {"dir1": "down", "dir2": "right", "push": "top"};

                new PNotify({
                    title: "Seigneur Modus la Région : " + val + " vient de réagir à votre appel",
                    text: "Les endroits clignotants réprensent chaque pays sur le globle louant votre puissance Seigneur Modus",
                    addclass: "stack-custom",
                    stack: myStack
                })
            };

            eventSource.onerror = (error) => {
                console.info("Debut du Process 'receiveError'");
                console.info(error);
            };
            setTimeout(() => {
                eventSource.close();
            },5000)
        }, 800 * index);
    });
}

function readByLanguage() {
    language.forEach((val, index) => {
        setTimeout(() => {
            let eventSource = new EventSource("country/all/language/" + val);

            eventSource.onmessage = (message) => {
                console.info("Debut du Process 'receiveData'");
                console.info("Raw Data : " + message.data);
                let data = JSON.parse(message.data);
                addPing(data);
            };

            eventSource.onopen = (e) => {
                console.info("Debut du Process 'countrySourceIsOpen'");
                console.info("Data : " + e);

                var myStack = {"dir1": "down", "dir2": "right", "push": "top"};
                new PNotify({
                    title: "Seigneur Modus les pays parlant la langue : " + val + " viennent de réagir à votre appel",
                    text: "Les endroits clignotants réprensent chaque pays sur le globle louant votre puissance Seigneur Modus",
                    addclass: "stack-custom",
                    stack: myStack
                })
            };

            eventSource.onerror = (error) => {
                console.info("Debut du Process 'receiveError'");
                console.info(error);
            };
            setTimeout(() => {
                eventSource.close();
            },5000)
        }, 1000 * index);
    });
}

function readAllCountry() {
    let eventSource = new EventSource("country/all");

    eventSource.onmessage = (message) => {
        console.info("Debut du Process 'receiveData'");
        console.info("Raw Data : " + message.data);
        let data = JSON.parse(message.data);
        addPing(data);
        listCountry.push(data);
        refreshGrid();
    };

    eventSource.onopen = (e) => {
        console.info("Debut du Process 'countrySourceIsOpen'");
        console.info("Data : " + e);
        var myStack = {"dir1": "down", "dir2": "right", "push": "top"};
        new PNotify({
            title: "Les pays réagisses à votre appels Seigneur Modus",
            text: "Les endroits clignotants réprensent chaque pays sur le globle louant votre puissance Seigneur Modus",
            addclass: "stack-custom",
            stack: myStack
        })
    };

    eventSource.onerror = (error) => {
        console.info("Debut du Process 'receiveError'");
        console.info(error);
    };

    setTimeout(() => {
        eventSource.close();
    },5000)
}

function addPing(data) {
    console.info("Debut du Process 'addPing'");
    let latLng = data.latlng;
    console.info(latLng);
    let lat = latLng[0];
    let lng = latLng[1];
    let color = countryColors[Math.floor(Math.random() * countryColors.length)];
    planet.plugins.pings.add(lng, lat, {color: color, ttl: 2000});
}

function initGrid() {
    $('#countryGuid').jsGrid({
        height: "auto",
        width: "100%",

        editing: false,
        sorting: true,
        paging: true,
        autoload: false,

        onInit: (grid) => {
            console.log('start onInit.');
        },

        onError: (grid, args) => {
            console.log('... start onError.');
            console.log(grid);
            console.log(args);
            console.log('... end onError.');
        },

        onDataLoaded: (d) => {
            console.log('... start onDataLoaded');
        },
        controller: {
            loadData: () => {
                return listCountry;
            }
        },
        fields: [
            {
                title: "Pays",
                name: "name",
                align: "center",
                type: "text",
                validate: "required",
                width: 80,
                editing: false
            },
            {
                title: "Capital",
                name: "capital",
                align: "center",
                type: "text",
                validate: "required",
                width: 80,
                editing: false
            }, {
                title: "Region",
                name: "region",
                align: "center",
                type: "text",
                validate: "required",
                width: 80,
                editing: false
            }
        ],
        rowClick: function (args) {
            console.info('... start rowClick');
            console.info(args);
            addPing(args.item);
        }
    });
}

function refreshGrid() {
    $("#countryGuid").jsGrid("loadData");
}


$('document').ready(function () {

    planet.loadPlugin(planetaryjs.plugins.earth({
        topojson: {file: 'js/planetary/world-110m.json'},
        oceans: {fill: '#000080'},
        land: {fill: '#339966'},
        borders: {stroke: '#008000'}
    }));

    planet.loadPlugin(planetaryjs.plugins.zoom({
        scaleExtent: [200, 1000],
        onZoom: function () {
            console.log("The planet was zoomed!", this, d3.event);
        }
    }));

    planet.loadPlugin(planetaryjs.plugins.drag({
        onDrag: function () {
            console.log("The planet was dragged!", this, d3.event);
        }
    }));

    planet.loadPlugin(planetaryjs.plugins.pings({
        color: 'yellow', ttl: 5000, angle: 10
    }));

    planet.projection.scale(300).translate([300, 300]);
    let canvas = document.getElementById('globe');
    planet.draw(canvas);

    readAllCountry();

    initGrid();
});