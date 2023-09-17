package com.example.application.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.application.R
import com.example.application.entities.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingTeams (private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingTeams", "Pre-populating database...")
            fillWithStartingTeams(context)
        }
    }

    /**
     * Pre-populate database with hard-coded users
     */
    private fun fillWithStartingTeams(context: Context) {
        val teams = listOf(
            Team(
                1,
                "Argentinos Juniors",
                3,
                0,
                1904,
                "Estadio Diego Armando Maradona",
                24000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/Asociacion_Atletica_Argentinos_Juniors.svg/240px-Asociacion_Atletica_Argentinos_Juniors.svg.png",
                5,
                "Buenos Aires"
            ),
            Team(
                2,
                "Arsenal",
                0,
                0,
                1957,
                "Estadio Julio Humberto Grondona",
                16350,
                "https://upload.wikimedia.org/wikipedia/en/thumb/6/60/Arsenal_Sarand%C3%AD_logo.svg/210px-Arsenal_Sarand%C3%AD_logo.svg.png",
                1,
                "Sarandí"
            ),
            Team(
                3,
                "Atlético Tucumán",
                1,
                0,
                1902,
                "Estadio Monumental José Fierro",
                35300,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Logo_del_Club_Atl%C3%A9tico_Tucum%C3%A1n_-_2017.svg/207px-Logo_del_Club_Atl%C3%A9tico_Tucum%C3%A1n_-_2017.svg.png",
                2,
                "San Miguel de Tucumán"
            ),
            Team(
                4,
                "Banfield",
                1,
                0,
                1896,
                "Estadio Florencio Sola",
                34500,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/16/CA_Banfield_%282014%29.svg/147px-CA_Banfield_%282014%29.svg.png",
                1,
                "Banfield"
            ),
            Team(
                5,
                "Belgrano",
                0,
                0,
                1905,
                "Estadio Julio César Villagra",
                28009,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Escudo_Oficial_del_Club_Atl%C3%A9tico_Belgrano.png/285px-Escudo_Oficial_del_Club_Atl%C3%A9tico_Belgrano.png",
                0,
                "Córdoba"
            ),
            Team(
                6,
                "Boca Juniors",
                34,
                18,
                1905,
                "La Bombonera",
                54467,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/CABJ70.png/207px-CABJ70.png",
                6,
                "Buenos Aires"
            ),
            Team(
                7,
                "Central Córdoba",
                0,
                0,
                1919,
                "Estadio Alfredo Terrera",
                15492,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/27/Escudo_del_Club_Central_C%C3%B3rdoba_de_Santiago_del_Estero.svg/225px-Escudo_del_Club_Central_C%C3%B3rdoba_de_Santiago_del_Estero.svg.png",
                0,
                "Santiago del Estero"
            ),
            Team(
                8,
                "Colón",
                0,
                0,
                1905,
                "Estadio Brigadier General Estanislao López",
                40686,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Escudo_del_C._A._Col%C3%B3n.png/216px-Escudo_del_C._A._Col%C3%B3n.png",
                0,
                "Santa Fe"
            ),
            Team(
                9,
                "Defensa y Justicia",
                1,
                0,
                1935,
                "Estadio Norberto Tomaghello",
                10000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Escudo_del_Club_Social_y_Deportivo_Defensa_y_Justicia.svg/240px-Escudo_del_Club_Social_y_Deportivo_Defensa_y_Justicia.svg.png",
                0,
                "Florencio Varela"
            ),
            Team(
                10,
                "Estudiantes de La Plata",
                6,
                4,
                1905,
                "Estadio Jorge Luis Hirschi",
                30000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Escudo_de_Estudiantes_de_La_Plata.svg/188px-Escudo_de_Estudiantes_de_La_Plata.svg.png",
                1,
                "La Plata"
            ),
            Team(
                11,
                "Gimnasia y Esgrima La Plata",
                1,
                0,
                1887,
                "Estadio Juan Carmelo Zerillo",
                24200,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Gimnasia_y_Esgrima_La_Plata_logo.png/200px-Gimnasia_y_Esgrima_La_Plata_logo.png",
                0,
                "La Plata"
            ),
            Team(
                12,
                "Godoy Cruz",
                0,
                0,
                1921,
                "Estadio Malvinas Argentinas",
                42000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/GCAT.png/225px-GCAT.png",
                0,
                "Mendoza"
            ),
            Team(
                13,
                "Huracán",
                7,
                0,
                1908,
                "Estadio Tomás Adolfo Ducó",
                48000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Emblema_oficial_del_Club_Atl%C3%A9tico_Hurac%C3%A1n.svg/148px-Emblema_oficial_del_Club_Atl%C3%A9tico_Hurac%C3%A1n.svg.png",
                1,
                "Buenos Aires"
            ),
            Team(
                14,
                "Independiente",
                38,
                17,
                1905,
                "Estadio Libertadores de América",
                48314,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Escudo_del_Club_Atl%C3%A9tico_Independiente.svg/228px-Escudo_del_Club_Atl%C3%A9tico_Independiente.svg.png",
                7,
                "Avellaneda"
            ),
            Team(
                15,
                "Lanús",
                1,
                0,
                1915,
                "Estadio Ciudad de Lanús – Néstor Díaz Pérez",
                47245,
                "https://upload.wikimedia.org/wikipedia/commons/9/93/Modificacion_flyer_para_Editat%C3%B3n_del_Centenario_del_Club_Atl%C3%A9tico_Lan%C3%BAs.png",
                0,
                "Lanús"
            ),
            Team(
                16,
                "Newell's Old Boys",
                6,
                0,
                1903,
                "Estadio Marcelo Bielsa",
                42000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/69/Escudo_del_Club_Atl%C3%A9tico_Newell%27s_Old_Boys_de_Rosario.svg/731px-Escudo_del_Club_Atl%C3%A9tico_Newell%27s_Old_Boys_de_Rosario.svg.png",
                1,
                "Rosario"
            ),
            Team(
                17,
                "Patronato",
                0,
                0,
                1914,
                "Estadio Presbítero Bartolomé Grella",
                22000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Escudo_del_Club_Atl%C3%A9tico_Patronato_de_la_Juventud_Cat%C3%B3lica_%282022%29.svg/664px-Escudo_del_Club_Atl%C3%A9tico_Patronato_de_la_Juventud_Cat%C3%B3lica_%282022%29.svg.png",
                0,
                "Paraná"
            ),
            Team(
                18,
                "Platense",
                0,
                0,
                1905,
                "Estadio Ciudad de Vicente López",
                27640,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Escudo_del_Club_Alt%C3%A9tico_Platense.svg/190px-Escudo_del_Club_Alt%C3%A9tico_Platense.svg.png",
                0,
                "Vicente López"
            ),
            Team(
                19,
                "Racing Club",
                18,
                9,
                1903,
                "Estadio Presidente Perón",
                53000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Escudo_de_Racing_Club_%282014%29.svg/198px-Escudo_de_Racing_Club_%282014%29.svg.png",
                1,
                "Avellaneda"
            ),
            Team(
                20,
                "River Plate",
                38,
                12,
                1901,
                "Estadio Monumental",
                84567,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Escudo_del_C_A_River_Plate.svg/194px-Escudo_del_C_A_River_Plate.svg.png",
                14,
                "Buenos Aires"
            ),
            Team(
                21,
                "Rosario Central",
                4,
                0,
                1889,
                "Estadio Gigante de Arroyito",
                41359,
                "https://upload.wikimedia.org/wikipedia/commons/c/c0/Rosario_Central_logo.png",
                0,
                "Rosario"
            ),
            Team(
                22,
                "San Lorenzo",
                15,
                1,
                1908,
                "Estadio Pedro Bidegain",
                47389,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Escudo_del_Club_Atl%C3%A9tico_San_Lorenzo_de_Almagro.svg/240px-Escudo_del_Club_Atl%C3%A9tico_San_Lorenzo_de_Almagro.svg.png",
                1,
                "Buenos Aires"
            ),
            Team(
                23,
                "Talleres",
                0,
                0,
                1913,
                "Estadio Mario Alberto Kempes",
                57000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Escudo_Talleres_2015.svg/231px-Escudo_Talleres_2015.svg.png",
                0,
                "Córdoba"
            ),
            Team(
                24,
                "Unión Santa Fe",
                0,
                0,
                1907,
                "Estadio 15 de Abril",
                27225,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/Escudo_club_Atl%C3%A9tico_Uni%C3%B3n_de_santa_fe.svg/240px-Escudo_club_Atl%C3%A9tico_Uni%C3%B3n_de_santa_fe.svg.png",
                0,
                "Santa Fe"
            ),
            Team(
                25,
                "Vélez Sarsfield",
                10,
                0,
                1910,
                "Estadio José Amalfitani",
                49694,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Escudo_del_Club_Atl%C3%A9tico_V%C3%A9lez_Sarsfield.svg/210px-Escudo_del_Club_Atl%C3%A9tico_V%C3%A9lez_Sarsfield.svg.png",
                1,
                "Buenos Aires"
            )
        )
        val dao = AppDatabase.getInstance(context)?.teamDao()

        teams.forEach {
            dao?.insertTeam(it)
        }
    }
}