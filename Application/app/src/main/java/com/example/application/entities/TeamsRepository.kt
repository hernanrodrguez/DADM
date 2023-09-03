package com.example.application.entities

class TeamsRepository {
    var teams : MutableList<Team> = mutableListOf()
    init {
        teams.add(Team("River Plate", 38, 12, 1901, "Mas Monumental", 84567, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Escudo_del_C_A_River_Plate.svg/194px-Escudo_del_C_A_River_Plate.svg.png", 14, "Buenos Aires"))
        teams.add(Team("Boca Juniors", 35, 18, 1905, "La Bombonera", 49000, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/CABJ70.png/207px-CABJ70.png", 17, "Buenos Aires"))
        teams.add(Team("Independiente", 16, 18, 1905, "Libertadores de America", 44220, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Escudo_del_Club_Atl%C3%A9tico_Independiente.svg/228px-Escudo_del_Club_Atl%C3%A9tico_Independiente.svg.png", 9, "Avellaneda"))
        teams.add(Team("Racing", 18, 3, 1903, "Cilindro de Avellaneda", 51389, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Escudo_de_Racing_Club_%282014%29.svg/198px-Escudo_de_Racing_Club_%282014%29.svg.png", 15, "Avellaneda"))
        teams.add(Team("San Lorenzo", 15, 3, 1908, "Nuevo Gasometro", 43963, "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Escudo_del_Club_Atl%C3%A9tico_San_Lorenzo_de_Almagro.svg/240px-Escudo_del_Club_Atl%C3%A9tico_San_Lorenzo_de_Almagro.svg.png", 2, "Buenos Aires"))
        teams.add(Team("Argentinos Juniors", 3, 2, 1904, "Diego Maradona", 24500, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Asociacion_Atletica_Argentinos_Juniors.svg/240px-Asociacion_Atletica_Argentinos_Juniors.svg.png", 0, "Buenos Aires"))
        teams.add(Team("Arsenal", 1, 2, 1957, "Julio Grondona", 16300, "https://upload.wikimedia.org/wikipedia/en/thumb/6/60/Arsenal_Sarand%C3%AD_logo.svg/210px-Arsenal_Sarand%C3%AD_logo.svg.png", 2, "Sarandi"))
        teams.add(Team("Atletico Tucuman", 0, 0, 1902, "Monumental Jose Fierro", 32700, "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Logo_del_Club_Atl%C3%A9tico_Tucum%C3%A1n_-_2017.svg/207px-Logo_del_Club_Atl%C3%A9tico_Tucum%C3%A1n_-_2017.svg.png", 0, "Tucuman"))
        teams.add(Team("Banfield", 1, 0, 1896, "Florencio Sola", 34901, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/CA_Banfield_%282014%29.svg/147px-CA_Banfield_%282014%29.svg.png", 1, "Banfield"))
        teams.add(Team("Barracas Central", 0, 0, 1904, "Claudio Tapia", 4400, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Barracas_central_logo.svg/185px-Barracas_central_logo.svg.png", 0, "Buenos Aires"))
        teams.add(Team("Belgrano", 0, 0, 1905, "Julio Villagra", 30000, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Escudo_Oficial_del_Club_Atl%C3%A9tico_Belgrano.png/285px-Escudo_Oficial_del_Club_Atl%C3%A9tico_Belgrano.png", 0, "Cordoba"))
        teams.add(Team("Central Cordoba", 0, 0, 1919, "Alfredo Terrera", 16000, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Escudo_del_Club_Central_C%C3%B3rdoba_de_Santiago_del_Estero.svg/225px-Escudo_del_Club_Central_C%C3%B3rdoba_de_Santiago_del_Estero.svg.png", 0, "Santiago del Estero"))
        teams.add(Team("Colon", 0, 0, 1905, "Cementerio de los Elefantes", 44220, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Escudo_del_C._A._Col%C3%B3n.png/216px-Escudo_del_C._A._Col%C3%B3n.png", 1, "Santa Fe"))
        teams.add(Team("Defensa y Justicia", 0, 2, 1935, "Norberto Tomaghello", 8000, "https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Escudo_del_Club_Social_y_Deportivo_Defensa_y_Justicia.svg/240px-Escudo_del_Club_Social_y_Deportivo_Defensa_y_Justicia.svg.png", 0, "Florencio Varela"))
        teams.add(Team("Estudiantes", 6, 6, 1905, "Estadio UNO", 32530, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Escudo_de_Estudiantes_de_La_Plata.svg/188px-Escudo_de_Estudiantes_de_La_Plata.svg.png", 2, "La Plata"))
        teams.add(Team("Gimnasia", 1, 0, 1887, "Juan Zerillo", 27000, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Gimnasia_y_Esgrima_La_Plata_logo.png/200px-Gimnasia_y_Esgrima_La_Plata_logo.png", 1, "La Plata"))
        teams.add(Team("Godoy Cruz", 0, 0, 1921, "Malvinas Argentinas", 45268, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/GCAT.png/225px-GCAT.png", 0, "Mendoza"))
        teams.add(Team("Huracan", 5, 0, 1908, "Palacio Duco", 48314, "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Emblema_oficial_del_Club_Atl%C3%A9tico_Hurac%C3%A1n.svg/148px-Emblema_oficial_del_Club_Atl%C3%A9tico_Hurac%C3%A1n.svg.png", 8, "Buenos Aires"))
        teams.add(Team("Instituto", 0, 0, 1918, "Juan Peron", 32535, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Escudo_Instituto_Atletico_Central_Cordoba.png/236px-Escudo_Instituto_Atletico_Central_Cordoba.png", 0, "Cordoba"))
    }
}