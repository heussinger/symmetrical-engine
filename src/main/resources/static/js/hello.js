

async function fetchData() {
    url = new URL("https://creativecommons.tankerkoenig.de/json/list.php");
    url.searchParams.append("lat", "51.547891");
    url.searchParams.append("lng", "9.906519");
    url.searchParams.append("rad", "2");
    url.searchParams.append("sort", "price");
    url.searchParams.append("type", "diesel");
    url.searchParams.append("apikey", "6da74308-8073-4f83-adfd-17618d977781");

    const result = await fetch(url);
    const data = await result.json();
    
    if (!data.ok) {
        document.body.innerHTML += "<p>Error fetching data: " + data.message + "</p>";
        return;
    }

    let bdy = document.getElementById('gasTableBody');

    data.stations.forEach(station => {
        let tr = document.createElement('tr');
        
        let tdName = document.createElement('td');
        tdName.textContent = station.name;
        tr.appendChild(tdName);

        let tdPrice = document.createElement('td');
        tdPrice.textContent = station.price;
        tr.appendChild(tdPrice);

        let tdDist = document.createElement('td');
        tdDist.textContent = station.dist;
        tr.appendChild(tdDist);
        
        bdy.appendChild(tr);
    });
}

fetchData();
