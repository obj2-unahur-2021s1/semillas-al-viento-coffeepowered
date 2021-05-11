package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) {
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  //No se usa bien la abstracción
  //parcelaTieneComplicaciones() tiene que ir en parcela, no en planta
  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

//No se pide saber si es o no transgenica, está demás. Simplicidad, YAGNI
class Soja(anioObtencionSemilla: Int, altura: Float, val esTransgenica: Boolean) : Planta(anioObtencionSemilla, altura) {

  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    //YAGNI
    return if (esTransgenica) horasBase * 2 else horasBase
  }


  override fun daSemillas(): Boolean  {
    //YAGNI
    if (this.esTransgenica) {
      return false
    }

    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}

//Falta clase soja transgenica que hereda de soja. Mutaciones controladas
