package utils

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class UserInputTest {

    @Test
    fun `readIntNotNull returns -1 for null input`() {
        // Simulate empty input
        System.setIn(ByteArrayInputStream("".toByteArray()))
        val result = readIntNotNull()
        assertEquals(-1, result)
    }

    @Test
    fun `readIntNotNull returns parsed integer for valid input`() {
        System.setIn(ByteArrayInputStream("42".toByteArray()))
        val result = readIntNotNull()
        assertEquals(42, result)
    }

    @Test
    fun `readNextInt returns integer for valid input`() {
        // Save original System.in and System.out
        val originalIn = System.`in`
        val originalOut = System.out

        try {
            // Set up input and capture output
            System.setIn(ByteArrayInputStream("42".toByteArray()))
            val outputStream = ByteArrayOutputStream()
            System.setOut(PrintStream(outputStream))

            val result = readNextInt("Enter a number: ")

            assertEquals(42, result)
            assertTrue(outputStream.toString().contains("Enter a number: "))
        } finally {
            // Restore original System.in and System.out
            System.setIn(originalIn)
            System.setOut(originalOut)
        }
    }

    // Add similar tests for other functions
    // For functions that need multiple inputs, use \n to separate them
    // Example: "invalid\n42" for first invalid then valid input
}