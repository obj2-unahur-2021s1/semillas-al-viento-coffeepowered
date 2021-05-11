package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  //Simplicidad KISS, no es necesaro cantidadPlantas.
  var cantidadPlantas = 0

  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
    //Redundancia mínima en ancho * largo pudiendo usar superficie() / 5.
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo

  fun plantar(planta: Planta) {
    //Se podía usar el tamaño de la lista de la planta y comparar si es mayor o igual a la cantidad de plantas que tolera
    //Redundancia mínima
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      //No pide un comentario sino que tire un error, robustez
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  var ahorrosEnPesos = 20000

  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
