package com.jjmf.mixfolio.domain.model

import androidx.annotation.DrawableRes
import com.jjmf.mixfolio.R

data class Ingrediente(
    val nombre: String,
    @DrawableRes val img: Int,
    val tipo: TipoIngrediente,
)

enum class TipoIngrediente {
    Alcohol,
    Gaseosa,
    Comun
}

val listIngredientes = listOf(
    Ingrediente(
        nombre = "Ron",
        img = R.drawable.ic_ron,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Vodka",
        img = R.drawable.ic_vodka,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Ginebra",
        img = R.drawable.ic_ginebra,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Tequila",
        img = R.drawable.ic_tequila,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Whisky",
        img = R.drawable.ic_whisky,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Limón",
        img = R.drawable.ic_limon,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Jarabe de azúcar",
        img = R.drawable.ic_jarabe_azucar,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Azúcar",
        img = R.drawable.ic_azucar,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Coca Cola",
        img = R.drawable.ic_coca_cola,
        tipo = TipoIngrediente.Gaseosa
    ),
    Ingrediente(
        nombre = "Sprite",
        img = R.drawable.ic_sprite,
        tipo = TipoIngrediente.Gaseosa
    ),
    Ingrediente(
        nombre = "Vino Tinto",
        img = R.drawable.vino_tinto,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Vino blanco",
        img = R.drawable.vino_blanco,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Aperol",
        img = R.drawable.ic_aperol,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Soda",
        img = R.drawable.ic_soda,
        tipo = TipoIngrediente.Gaseosa
    ),
    Ingrediente(
        nombre = "Hierbabuena",
        img = R.drawable.ic_hierbabuena,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Licor de naranja",
        img = R.drawable.ic_licor_naranja,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Crema de coco",
        img = R.drawable.ic_crema_coco,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Jugo de piña natural",
        img = R.drawable.ic_jugo_pinia,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Leche condensada",
        img = R.drawable.ic_leche_condensada,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Crema de leche",
        img = R.drawable.ic_crema_leche,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Vermouth rosso",
        img = R.drawable.ic_naranja,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Licor de café",
        img = R.drawable.ic_licor_cafe,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Café expresso",
        img = R.drawable.ic_cafe_expresso,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Jugo de arándanos",
        img = R.drawable.ic_arandanos,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Vodka citron",
        img = R.drawable.ic_vodka,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Agua tonica",
        img = R.drawable.ic_agua_tonica,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Jugo de naranja",
        img = R.drawable.ic_jugo_naranja,
        tipo = TipoIngrediente.Comun
    ),
    Ingrediente(
        nombre = "Campari",
        img = R.drawable.ic_campari,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Vermouth dry",
        img = R.drawable.ic_naranja,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Granadina",
        img = R.drawable.ic_granadina,
        tipo = TipoIngrediente.Alcohol
    ),
    Ingrediente(
        nombre = "Clara de huevo",
        img = R.drawable.ic_clara_huevo,
        tipo = TipoIngrediente.Comun
    )
)