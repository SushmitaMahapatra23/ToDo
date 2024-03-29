class Rectangle(val length: Double, val width: Double) {
    // Function to calculate the area of the rectangle
    fun calculateArea(): Double {
        return length * width
    }

    // Function to calculate the perimeter of the rectangle
    fun calculatePerimeter(): Double {
        return 2 * (length + width)
    }
}

