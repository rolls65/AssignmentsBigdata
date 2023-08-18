package org.itc
import scala.annotation.tailrec
import scala.collection.immutable.{HashSet, ListMap}
class AllAboutScalaTask {

}
object main {
  def main(args: Array[String]): Unit = {
    var norm: String = "http://allaboutscala.com/scala-exercises"

    norm = norm.toUpperCase()
    norm = norm.reverse
    println(norm)
    println("")

    val name: String = "Vanilla Donut"
    val numPurchased: String = "10"
    val price: Double = 2.5

    val donutJson =
      s"""
         |{
         |"donut_name":"$name",
         |"quantity_purchased":"$numPurchased",
         |"price":$price
         |}
          """.stripMargin
    println(donutJson)

    //Create a Scala program to prompt customers for their name and age. The format for the name and age labels should be in bold. And, the name literal should be underlined.
    //    println("What's your name")
    //    val nameInput = scala.io.StdIn.readLine()
    //    println(Console.BOLD)
    //    print("Name: ")
    //    print(Console.RESET)
    //    print(Console.UNDERLINED)
    //    print(nameInput)
    //    print(Console.RESET)
    //    println("\nHow old are you")
    //    val ageInput = scala.io.StdIn.readInt()
    //    print(Console.BOLD)
    //    print("Age: ")
    //    print(Console.RESET)
    //    print(ageInput)
    //Create a Scala program to find the 8th character in the String: "http://allaboutscala".
    val findString = "http://allaboutscala"
    println(findString.charAt(7))

    /*Create a Scala program to calculate the total cost for a customer who is buying 10 Glazed donuts.
   You can assume that the price of each Glazed donut item is at $2.50.
   Notice the format of the $25.00 total cost literal, which is essentially at 2 decimal places.
   */

    val donutsPurchased: Int = 10
    val donutPrice: Double = 2.50

    println("Total cost of 10 Glazed Donuts = $%.2f".format(donutPrice * donutsPurchased))

    //Create a Scala program that will ask the following question to a customer: "What is your favorite movie of all times?
    //    println("What is your favourite movie of all times?")
    //    val movieInput = scala.io.StdIn.readLine()
    //    println("%s is totally awesome!".format(movieInput))

    //Create a Scala program to output your name and favorite movie.
    //    println("First name?")
    //    val firstNameInput = scala.io.StdIn.readLine()
    //    println("Last name?")
    //    val lastNameInput = scala.io.StdIn.readLine()
    //    println("Favourite movie?")
    //    val favoriteMoveInput = scala.io.StdIn.readLine()
    //
    //    println(s"First Name: $firstNameInput")
    //    println(s"Last Name: $lastNameInput")
    //    println(s"Favourite Movie: $favoriteMoveInput")

    //Create a Scala program to parse the corresponding values from the given String "Vanilla Donut 10 2.25", where the literal Vanilla Donut is a particular donut, the 10 literal is the quantity purchased, and 2.25 is the unit price of each Vanilla Donut. You further need to cast each token from the input String to their corresponding types, such as, an Int, Double or
    val parseInput = "Vanilla Donut 10 2.25"
    val parseInputWords = parseInput.split(" ")
    println(parseInputWords(2))
    println("Donut Name: %s %s".format(parseInputWords(0), parseInputWords(1)))
    println("Donut Price: $%.2f".format(parseInputWords(3).toDouble))
    println("Donut Purcased: %d".format(parseInputWords(2).toInt))

    //Create a Scala program and use an appropriate data structure to present the following key and value pairs of children and their ages: Bill is 9 years old, Jonny is 8 years old, Tommy is 11 years old, and Cindy is 13 years old. Sort out the corresponding child to age in reverse alphabet order.
    val childrenNames: Map[String, Int] = Map("Bill" -> 9, "Jonny" -> 8, "Tommy" -> 11, "Cindy" -> 13)
    val sortedChildrenNames = ListMap(childrenNames.toSeq.sortWith(_._1 > _._1): _*)
    println(sortedChildrenNames)

    //10
    var firstBasket = List("Cake", "Milk", "Cheese", "Toilet Paper")
    var secondBasket = List("Bread", "Water", "Juice", "Milk", "Cheese")
    println(firstBasket.intersect(secondBasket))

    //11
    firstBasket = List("Milk", "Cheese", "Donuts", "Apples", "Bananas")
    println(firstBasket.filter(x => !x.equals("Apples") && !x.equals("Bananas")))

    //12
    val upperCharArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray
    for (i <- 0 to 1) {
      var codeString: String = ""
      for (j <- 0 to i) {
        codeString = codeString + upperCharArray(i)
      }
      codeString = codeString + " - %d".format(i + 1)
      println(codeString)
    }
    //13
    val tupleBasket = List(("T-Shirt", "Medium", 10.99), ("Polo-Shirt", "Large", 4.99), ("Vest", "Small", 5.99), ("T-Shirt", "Small", 4.99), ("T-Shirt", "Small", 4.99))
    tupleBasket.foreach({
      case x if x._1 == "T-Shirt" => println(s"${x._1.toUpperCase()} is priced at $$${x._3} for the ${x._2} size.")
      case x => println(s"${x._1.toLowerCase()} is priced at $$${x._3} for the ${x._2} size.")
    })

    //14
    val firstList = List("pencil", "pen", "sharpener")
    val secondList = List("math book", "french book", "english book")

    val thirdList = List(firstList, secondList).flatten
    thirdList.foreach(x => println(s"$x is required in the classroom"))

  }
}

object numbersObject {
  def questionThree(x: Int) = println(s"$x as String literal = %s".format(x.toString))

  def main(args: Array[String]) = {
    //1
    val numbersList = List.range(100, 111)
    var numbersListString: String = "Number range from 100 to 110 inclusive = "
    numbersList.foreach(x => numbersListString = numbersListString + s"$x ")
    println(numbersListString)

    //2
    var problem2List = List.range(100, 150)
    problem2List = problem2List.filter(x => x % 10 != 0)

    //3
    val numVector = Vector(0, 10, 20, 47, -2, 99, -98)
    println("The smallest item in the Vector = %d".format(numVector.min))
    println("The largest item in the Vector = %d".format(numVector.max))

    //4
    val four1List = HashSet(1,3,5,10,20) -- HashSet(20,17,18,199,0)
    val four2List = HashSet(20,17,18,199,0) -- HashSet(1,3,5,10,20)

    println(s"Number literals in set one but not in set two = $four1List")
    println(s"Number literals in set one but not in set two = $four2List")

    //5
    val fiveList = List(99.5, 100.0, 50.0, 55.0, 70.0, 100.0, -1.0) ++ List(10.0, 20.0, 30.0, 40.0, 50.0)
    println(s"Combined number literals = $fiveList")
    println("Lowest number literal = %.1f".format(fiveList.min))
    println("Largest number literal = %.1f".format(fiveList.max))

    //6
    val sixList = List(10, 77, 90, 50, 100, 110)
    println(s"Does magic number 77 exist within the numerical value of $sixList?")
    println(sixList.exists(x => {x == 77}).toString.toUpperCase)

    //7
    val sevenList = List(2, 8, 19, 20, 25, 50, 100, 10)
    val sevenDropped = sevenList.dropWhile(x => {x%2 == 0})
    println("Starting from left to right, removing items that are factors of two, and" +
      " will stop execution on the first element that is not divisible by two:")

    sevenDropped.foreach(println)

    //8
    val eightSeq = Seq("James", 7, "Andy", 8, "Tommy", 10, "Bob", 13, "Sam", 10)
    val ageSum = eightSeq.collect{case age: Int => age}
    println(s"Combined sequence of names to ages = $eightSeq")
    println(s"Sum of ages = ${ageSum.sum}")

    //9
    var nineList = List.range(301, 351 ,2)
    println("Odds between 300 and 350")
    println(nineList)
  }
}

object functionsTraits{
  def main(args: Array[String]): Unit = {
    //1
    val tuple1 = ("Rice", 10.99, 5)

    case class questionOne(name: String, price: Double, purchased: Int)
    val riceCase = questionOne(tuple1._1, tuple1._2, tuple1._3)
    println(s"A ${riceCase.name} is currently priced at $$${riceCase.price}, and the customer bought ${riceCase.purchased}")

    //2
    val iceCreamCase = questionOne("Vanilla ice cream", 2.99, 10)
    val chocolateBiscuitCase = questionOne("Chocolate Biscuit", 3.99, 3)
    val cupckaesCase = questionOne("Cupcakes", 4.99, 5)
    val basket = List(iceCreamCase, chocolateBiscuitCase, cupckaesCase)

    def printBasket = basket.foreach(x => println(s"${x.purchased} ${x.name} at $$${x.price} each"))

    printBasket

    def printOnlyVanillaIcecream(basket: List[questionOne]): Unit = {
      basket.foreach {
        case questionOne("Vanilla ice cream", _, _) => println(s"Found Vanilla ice cream")
        case questionOne(_, _, _) => println("Found another item")
      }
    }

    printOnlyVanillaIcecream(basket)

    //3
    def threeDef(x: Int):Unit = {
      println(s"$x as String literal = ${x.toString}")
    }
    threeDef(10)
    def threeSecondDef(ints:Int*):String = {ints.mkString(" :: ")}
    println(s"10, 11, 12 as String literals = ${threeSecondDef(10,11,12)}")

    //4
    class BasketValidator(){
      def validate[A](x:A): Unit = {
        x match{
          case x: String => println(s"Found a valid item = $x")
          case x: Double => println(s"Item $x of type Double is not valid")
          case x: Int => println(s"Item $x of type Int is not valid")
          case _ => println(s"Item $x should be removed from the basket")
        }
      }
    }
    object BasketValidator{
      def apply(): BasketValidator = new BasketValidator()
    }
    val fourBasket = List("Cupcake", 2.99, 100L, 7, "Ice Cream")
    fourBasket.foreach(BasketValidator().validate(_))

    //5
    val isJohn: PartialFunction[String, Boolean]={
      case x if(x.equals("John")) => true
    }

    val isJoe: PartialFunction[String, Boolean] = {
      case x if (x.equals("Joe")) => true
    }

    val JackJill: PartialFunction[String, Boolean] = {
      case "Jack" | "Jill" => true
    }

    val invalid: PartialFunction[String, Boolean] = {
      case _ => false
    }

    val validator = isJohn orElse isJoe orElse JackJill orElse invalid
    println(s"Name John is valid = ${validator("John")}")
    println(s"Name Jack is valid = ${validator("Jack")}")
    println(s"Name Bob is valid = ${validator("Bob")}")
    println(s"Name Jill is valid = ${validator("Jill")}")

    //6
    def toUpper(s:String) = {s.toUpperCase}
    def toLower(s:String) = {s.toLowerCase}
    def formatNames(s:String)(f:String => String): String = {
      f(s)
    }
    println(formatNames("Bob")(toUpper(_)))
    println(formatNames("Joe")(toLower(_)))

    //7
    case class student(name:String, age:Int)
    val john = student("John", 7)
    val jack = student("Jack", 13)
    val joe = student("Joe", 15)
    val jill = student("Jill", 15)
    val james = student("James", 11)

    val studentList = List(john,jack,joe,jill,james)


    @tailrec
    def walkThrough(students:List[student], i:Int): Boolean = {
      if (i == students.length){false}
      else if(students(i).age == 15){true}
      else {walkThrough(students, i+1)}
    }
    val foundStudent = walkThrough(studentList,  0)
    println(s"Is there a student who is 15 years old = $foundStudent")

    //8
    abstract class abstractStudent(name:String, age:Int){
      def printName(): Unit = {}
    }

    class Primary_student(name:String, age:Int) extends abstractStudent(name, age){
      override def printName(): Unit = {println(s"name = $name, age = $age")}
    }
    object Primary_student{
      def apply(name:String, age:Int): Primary_student = new Primary_student(name, age)
    }

    class Secondary_student(name: String, age: Int) extends abstractStudent(name, age) {
      override def printName(): Unit = {
        println(s"name = $name, age = $age")
      }
    }
    object Secondary_student{
      def apply(name:String, age:Int): Secondary_student = new Secondary_student(name,age)
    }

    val johnStudent = Primary_student("John", 8)
    val jillStudent = Primary_student("Jill", 10)
    val jamesStudent = Secondary_student("James",13)
    val joeStudent = Secondary_student("Joe",14)
    val jackStudent = Secondary_student("Jack",11)

    val students = List(johnStudent, jillStudent, jamesStudent, joeStudent, jackStudent)
    def listStudents(students: List[abstractStudent]): Unit = {
      students.foreach(x => x.printName())
    }
    listStudents(students)

    //9
    case class nineStudent(name:String, age:Int, favSnack: Option[String])
    val nineJack = nineStudent("Jack", 15, Some("cupcake"))
    val nineJill = nineStudent("Jill", 10, Some("ice cream"))
    val nineJoe = nineStudent("Joe", 7, Some("cupcake"))
    val nineJames = nineStudent("James", 10, Some("chocolate"))
    val nineJohn = nineStudent("John", 11, None)
    val nineJohnAgain = nineStudent("John", 11, Some("cupcake"))
    val nineStudents = List(nineJack,nineJill,nineJoe,nineJames,nineJohn,nineJohnAgain)

    def nineListStudents(students:List[nineStudent]): Unit = {
      students.foreach{
        x => println(s"name = ${x.name} is ${x.age} years old. Favorite snack is ${x.favSnack.getOrElse("nothing!")}")
      }
    }
    nineListStudents(nineStudents)

    //10
    case class Car(name:String, price:Double)
    type CarStock = (Car, Int)

    val vwPassatStock = new CarStock(Car("vw passat", 10000), 100)
    val vwGolfStock = new CarStock(Car("vw golf", 12000), 50)
    val bmw3Stock = new CarStock(Car("bmw 3 series", 20000), 200)
    val bmw5Stock = new CarStock(Car("bmw 5 series", 50000), 75)
    val mazdaStock = new CarStock(Car("mazda 3", 15000), 49)
    val carInventory = List(vwPassatStock, vwGolfStock, bmw3Stock, bmw5Stock, mazdaStock)

    def orderStock(inv:List[CarStock]): Unit = {
      inv.sortBy(_._2).foreach{
        case (car, stock) => println(s"${car.name} stock = $stock")
      }
    }
    println(s"Cars sorted by lowest stock: ")
    orderStock(carInventory)

    //11
    case class elevenCar(name: String, price: Double)

    class CarUUID(car: elevenCar) {
      def uuid: String = s"car uuid = ${car.name} - ${car.name.hashCode}"
    }


    implicit def uuid(car: elevenCar): CarUUID = new CarUUID(car)

    val bmw3 = elevenCar("bmw 3 series", 20000)
    val bmw5 = elevenCar("bmw 5 series", 50000)
    val vwPassat = elevenCar("vw passat", 10000)
    val vwGolf = elevenCar("vw golf", 12000)
    val mazda3 = elevenCar("mazda 3", 15000)

    val cars = List(bmw3, bmw5, vwPassat, vwGolf, mazda3)
    cars.foreach(car => println(car.uuid))


    //12
    case class twelveCar(name: String, price: Double)

    implicit class CarUUID12(car: twelveCar) {
      def uuid: String = s"car uuid = ${car.name} - ${car.name.hashCode}"
    }

    val bmw312 = twelveCar("bmw 3 series", 20000)
    val bmw512 = twelveCar("bmw 5 series", 50000)
    val vwPassat12 = twelveCar("vw passat", 10000)
    val vwGolf12 = twelveCar("vw golf", 12000)
    val mazda312 = twelveCar("mazda 3", 15000)

    val cars12 = List(bmw312, bmw512, vwPassat12, vwGolf12, mazda312)
    cars.foreach(car => println(car.uuid))


    //13
    abstract class Student13(name: String, age: Int) {
      def studentId(): Unit
    }

    case class PrimarySchoolStudent(name: String, age: Int) extends Student13(name, age) {
      override def studentId(): Unit = {
        println(s"[PrimarySchoolStudent] - name: $name - age: $age")
      }
    }

    case class SecondarySchoolStudent(name: String, age: Int) extends Student13(name, age) {
      override def studentId(): Unit = {
        println(s"[SecondaySchoolStudent] - name: $name - age: $age")
      }
    }

    class School[+S <: Student13](students: List[S]) {
      def printStudents(): Unit = {
        students.foreach(_.studentId())
      }
    }

    val jill13: PrimarySchoolStudent = PrimarySchoolStudent("Jill", 8)
    val joe13: PrimarySchoolStudent = PrimarySchoolStudent("Joe", 7)
    val jack13: SecondarySchoolStudent = SecondarySchoolStudent("Jack", 15)
    val james13: Student13 = SecondarySchoolStudent("James", 10)
    val john13: Student13 = SecondarySchoolStudent("John", 11)

    val students13 = List(jack13, james13, john13, jill13, joe13)
    val primarySchool = new School(students13)
    primarySchool.printStudents()

    //14
    abstract class Vehicle(val make:String)
    case class Car14(override val make:String) extends Vehicle(make)
    case class Bike(override val make:String) extends Vehicle(make)

    object VehicleReport{
      def printVehicles[V <: Vehicle](vehicles: List[V]): Unit = {
        vehicles.foreach(x => println(x.make))
      }
    }

    val bmwCar = Car14("bmw 3 series")
    val vwCar = Car14("vw golf")
    val bmwBike = Bike("bmw g 310 r bike")
    val hondaBike = Bike("fire storm bike")
    val vehicles = List(bmwCar, vwCar, bmwBike, hondaBike)
    VehicleReport.printVehicles(vehicles)

    //15
    abstract class Donut(name: String) {
      def printName(): Unit
    }

    case class VanillaDonut(name: String) extends Donut(name) {
      def printName(): Unit = println(name)
    }

    case class GlazedDonut(name: String) extends Donut(name) {
      def printName(): Unit = println(name)
    }

    val vanillaDonut = VanillaDonut("Vanilla Donut")
    val glazedDonut = GlazedDonut("Glazed Donut")

    class Pastry[P <: Donut](pastry: P) {
      def name(): Unit = pastry.printName()
    }

    val vanillaPastry = new Pastry[VanillaDonut](vanillaDonut)
    vanillaPastry.name()

    val glazedPastry = new Pastry[Donut](glazedDonut)
    glazedPastry.name()

    //16
    case class Lollipop(name:String)
    val lollipop = new Lollipop("Lollipop")
    class ShoppingCart(){
      def addCartItem[P >: Lollipop](item: P):Unit = {
        println(s"Adding $item to shopping cart")
        println(item.getClass)
      }
    }
    val shoppingCart = new ShoppingCart()
    shoppingCart.addCartItem(vanillaDonut)
    shoppingCart.addCartItem(glazedDonut)
    shoppingCart.addCartItem(lollipop)
    shoppingCart.addCartItem("oops something is not right!")

    //17
    case class CartItem(name:String, quantity:Int, price: Double)

    object ShoppingCart{
      def totalCost(cartItem: CartItem, couponCode:Option[String]): Double = {

        couponCode match {
          case Some(couponCode) =>
            val discount = if (couponCode == "COUPON_1234") 0.1 else 0.0
            val totalCost = cartItem.price * cartItem.quantity * (1 - discount)
            totalCost
          case None => cartItem.price * cartItem.quantity
        }
      }
    }

    val cartItem = CartItem("Chocolate", 10, 2.50)
    println(s"""Total cost without couponCode = $$${ShoppingCart.totalCost(cartItem, None)}""")
    println(s"""Total cost with couponCode = $$${ShoppingCart.totalCost(cartItem, Some("COUPON_1234"))}""")

    //18
    case class Vehicle18(name: String)
    trait VehicleInventory{
      def create(vehicle:Vehicle18)
      def read(vehicle:Vehicle18)
      def update(vehicle:Vehicle18)
      def delete(vehicle:Vehicle18)
    }

    class VehicelInventorySystem extends VehicleInventory{
      def create(vehicle: Vehicle18): Unit = {
        println(s"Create vehicle = $vehicle")
      }

      def read(vehicle: Vehicle18): Unit = {
        println(s"Read vehicle = $vehicle")
      }

      def update(vehicle: Vehicle18): Unit = {
        println(s"Update vehicle = $vehicle")
      }

      def delete(vehicle: Vehicle18): Unit = {
        println(s"Delete vehicle = $vehicle")
      }
    }

    val carBmw5 = Vehicle18("bmw car 5 series")

    val vehicleInventorySystem = new VehicelInventorySystem()
    vehicleInventorySystem.create(carBmw5)
    vehicleInventorySystem.read(carBmw5)
    vehicleInventorySystem.update(carBmw5)
    vehicleInventorySystem.delete(carBmw5)

    //19
    abstract class Vehicle19(name: String)
    case class Car19(name: String) extends Vehicle(name)
    case class Bike19(name: String) extends Vehicle(name)

    trait VehicleInventory19[V]{
      def create(vehicle: V)
      def read(vehicle: V)
      def update(vehicle: V)
      def delete(vehicle: V)
    }

    class VehicleInventorySystem[V] extends VehicleInventory19[V] {
      def create(vehicle: V): Unit = {
        println(s"Create vehicle $vehicle")
      }

      def read(vehicle: V): Unit = {
        println(s"Read vehicle $vehicle")
      }

      def update(vehicle: V): Unit = {
        println(s"Update vehicle $vehicle")
      }

      def delete(vehicle: V): Unit = {
        println(s"Delete vehicle $vehicle")
      }
    }
    val carBmw519 = Car19("bmw car 5 series")
    val carMazda519 = Car19("mazda car 3 series")
    val bikeHonda19 = Bike19("honda bike firestorm")
    val bikeBmwR19 = Bike19("bmw bike r 2000")

    val vehicleInventorySystem19 = new VehicleInventorySystem[Vehicle]()
    vehicleInventorySystem19.create(carBmw519)
    vehicleInventorySystem19.read(carMazda519)
    vehicleInventorySystem19.update(bikeHonda19)
    vehicleInventorySystem19.delete(bikeBmwR19)

    //20
    trait Vehicle20{
      def printName()
    }

    class Car20(name:String) extends Vehicle20{
      override def printName(): Unit = {
        println(name)
      }
    }

    class BmwCar(name:String) extends Car20(name){
      override def printName(): Unit = println(name)
    }

    class MazdaCar(name:String) extends Car20(name){
      override def printName(): Unit = println(name)
    }

    class Bike20(name:String) extends Vehicle20{
      override def printName(): Unit = println(name)
    }

    class HondaBike(name:String) extends Bike20(name){
      override def printName(): Unit = println(name)
    }
    class BmwBike(name: String) extends Bike20(name) {
      override def printName(): Unit = println(name)
    }

    class VehicleMaker[V <: Vehicle20](vehicle: V){
      def make(): V = {
        print("Making vehicle = ")
        vehicle.printName()
        vehicle
      }
    }

    val carsToMake = List(new BmwCar("bmw car 5 series"), new MazdaCar("mazda car 3 series"))
    val bikesToMake = List(new HondaBike("honda bike firestorm"), new BmwBike("bmw bike r 2000"))
    val vehiclesToMake = carsToMake ++ bikesToMake

    vehiclesToMake.map { v =>
      new VehicleMaker[Vehicle20](v).make()
    }

    //21
    class VehicleMaker21[V <: Vehicle20](vehicle: Option[V] = None){
      def makeSimilarCars[A <: V, B <: V](vehicle1: A, vehicle2: B)(implicit x: A=:=B): Unit  = {
        println("Making two CAR vehicles: ")
        print("vehicleA = ")
        vehicle1.printName()
        print("vehicleB = ")
        vehicle2.printName()
      }

      def makeBikes[A <: V, B <: V](vehicle1: A, vehicle2: B)(implicit ev: A <:< V): Unit = {
        println("Making two BIKE vehicles: ")
        print("vehicleA = ")
        vehicle1.printName()
        print("vehicleB = ")
        vehicle2.printName()
      }
    }

    val bmw5Car = new BmwCar("bmw car 5 series")
    val bmw3Car = new BmwCar("bmw car 3 series")
    val hondaBike21 = new HondaBike("honda bike firestorm")
    val bmwBike21 = new BmwBike("bmw bike r 2000")

    new VehicleMaker21[Vehicle20]().makeSimilarCars(bmw3Car, bmw5Car)
    new VehicleMaker21[Vehicle20]().makeBikes(hondaBike21, bmwBike21)

    //22
    abstract class Vehicle22(name: String)
    case class Car22(name: String) extends Vehicle22(name)
    case class Bike22(name: String) extends Vehicle22(name)

    trait VehicleDatabaseService[V <: Vehicle22] {
      protected def addOrUpdate(vehicle: V): Unit = {
        println(s"Adding or updating vehicle = $vehicle")
      }

      protected def get(vehicle: V): Unit = {
        println(s"Getting vehicle = $vehicle")
      }

      protected def remove(vehicle: V): Unit = {
        println(s"Removing vehicle = $vehicle")
      }
    }

    trait VehicleInventory22[V <: Vehicle22] {
      def create(vehicle: V): Unit
      def read(vehicle: V): Unit
      def update(vehicle: V): Unit
      def delete(vehicle: V): Unit
    }

    class VehicleInvetorySystem[V <: Vehicle22] extends VehicleInventory22[V]
      with VehicleDatabaseService[V] {

      override def create(vehicle: V): Unit = {
        println(s"Create vehicle = $vehicle")
        addOrUpdate(vehicle)
      }

      override def read(vehicle: V): Unit = {
        println(s"Read vehicle = $vehicle")
        get(vehicle)
      }

      override def update(vehicle: V): Unit = {
        println(s"Update vehicle = $vehicle")
        addOrUpdate(vehicle)
      }

      override def delete(vehicle: V): Unit = {
        println(s"Delete vehicle = $vehicle")
        remove(vehicle)
      }
    }

    val vehicleInventorySystem22 = new VehicleInvetorySystem[Vehicle22]()
    vehicleInventorySystem22.create(Car22("bmw 3 series"))
    vehicleInventorySystem22.read(Car22("mazda 3 series"))
    vehicleInventorySystem22.update(Bike22("honda bike firestorm"))
    vehicleInventorySystem22.delete(Bike22("bmw bike r 2000"))
    println("")

    //23
    trait VehicleSystem[V <: Vehicle22]{
      val vehicleInventorySystem23: VehicleInventorySystem[V]

      def checkVehicleStock(vehicle: V): Unit = {
        println(s"Checking vehicle stock for vehicle = $vehicle")
        vehicleInventorySystem23.read(vehicle)
      }
    }

    val vehicleSystem = new VehicleSystem[Vehicle22] {
      override val vehicleInventorySystem23: VehicleInventorySystem[Vehicle22] = new VehicleInventorySystem[Vehicle22]
    }

    vehicleSystem.checkVehicleStock(Car22("mazda 3 series"))
    vehicleSystem.checkVehicleStock(Bike22("honda bike firestorm"))
    println("")

    //24
    class VehicleInventoryService[V <: Vehicle22] {
      def checkStock(vehicle: V): Unit = {
        println(s"checking stock for vehicle = $vehicle")
      }
    }

    class VehiclePricingService[V <: Vehicle22] {
      def checkPrice(vehicle: V): Unit = {
        println(s"checking price for vehicle = $vehicle")
      }
    }

    trait VehicleServices[V <: Vehicle22] {
      lazy val vehicleInventoryService = new VehicleInventoryService[V]
      lazy val vehiclePricingService = new VehiclePricingService[V]
    }

    trait VehicleSystem24[V <: Vehicle22] {
      this: VehicleServices[V] =>

      def buyVehicle(vehicle: V): Unit = {
        println(s"buying vehicle $vehicle")
        vehicleInventoryService.checkStock(vehicle)
        vehiclePricingService.checkPrice(vehicle)
      }
    }
    object VehicleApp extends VehicleSystem24[Vehicle22] with VehicleServices[Vehicle22]
    VehicleApp.buyVehicle(Car22("mazda 3 series"))
    VehicleApp.buyVehicle(Bike22("honda bike firestorm"))


  }
}
