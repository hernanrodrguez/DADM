package com.example.application.entities

class TeamsRepository {
    var teams : MutableList<Team> = mutableListOf()
    init {
        teams.add(Team("River Plate", 38, 12, 1901, "Mas Monumental", 84567, "https://www.promiedos.com.ar/images/30/18.png"))
        teams.add(Team("Boca Juniors", 35, 18, 1905, "La Bombonera", 49000, "https://www.promiedos.com.ar/images/30/6.png"))
        teams.add(Team("Independiente", 16, 18, 1905, "Libertadores de America", 44220, "https://www.promiedos.com.ar/images/30/12.png"))
        teams.add(Team("Racing", 18, 3, 1903, "Cilindro de Avellaneda", 51389, "https://www.promiedos.com.ar/images/30/17.png"))
        teams.add(Team("San Lorenzo", 15, 3, 1908, "Nuevo Gasometro", 43963, "https://www.promiedos.com.ar/images/30/19.png"))
        teams.add(Team("Argentinos Juniors", 3, 2, 1904, "Diego Maradona", 24500, "https://www.promiedos.com.ar/images/30/3.png"))
        teams.add(Team("Arsenal", 1, 2, 1957, "Julio Grondona", 16300, "https://www.promiedos.com.ar/images/30/4.png"))
        teams.add(Team("Atletico Tucuman", 0, 0, 1902, "Monumental Jose Fierro", 32700, "https://www.promiedos.com.ar/images/30/25.png"))
        teams.add(Team("Banfield", 1, 0, 1896, "Florencio Sola", 34901, "https://www.promiedos.com.ar/images/30/5.png"))
        teams.add(Team("Barracas Central", 0, 0, 1904, "Claudio Tapia", 4400, "https://www.promiedos.com.ar/images/30/82.png"))
        teams.add(Team("Belgrano", 0, 0, 1905, "Julio Villagra", 30000, "https://www.promiedos.com.ar/images/30/26.png"))
        teams.add(Team("Central Cordoba", 0, 0, 1919, "Alfredo Terrera", 16000, "https://www.promiedos.com.ar/images/30/78.png"))
        teams.add(Team("Colon", 0, 0, 1905, "Cementerio de los Elefantes", 44220, "https://www.promiedos.com.ar/images/30/7.png"))
        teams.add(Team("Defensa y Justicia", 0, 2, 1935, "Norberto Tomaghello", 8000, "https://www.promiedos.com.ar/images/30/29.png"))
        teams.add(Team("Estudiantes", 6, 6, 1905, "Estadio UNO", 32530, "https://www.promiedos.com.ar/images/30/8.png"))
        teams.add(Team("Gimnasia", 1, 0, 1887, "Juan Zerillo", 27000, "https://www.promiedos.com.ar/images/30/9.png"))
        teams.add(Team("Godoy Cruz", 0, 0, 1921, "Malvinas Argentinas", 45268, "https://www.promiedos.com.ar/images/30/10.png"))
        teams.add(Team("Huracan", 5, 0, 1908, "Palacio Duco", 48314, "https://www.promiedos.com.ar/images/30/11.png"))
        teams.add(Team("Instituto", 0, 0, 1918, "Juan Peron", 32535, "https://www.promiedos.com.ar/images/30/33.png"))
    }
}