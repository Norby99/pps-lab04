package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import tasks.adts.SchoolModel.BasicSchoolModule.*
import u03.extensionmethods.Sequences.*
import u03.extensionmethods.Sequences.Sequence.*

class SchoolModelTest:
    
    val john: Teacher = teacher("John")
    val albert: Teacher = teacher("Albert")
    
    val math: Course = course("Math")
    val italian: Course = course("Italian")

    @Test def testTeacher() =
        assertEquals("John", john)
    
    @Test def testCourse() =
        assertEquals("Math", math)
        
    @Test def testEmptySchool() =
        assertEquals(Nil(), emptySchool.courses)
        assertEquals(Nil(), emptySchool.teachers)
        
    @Test def testCourses() =
        val v1= emptySchool.setTeacherToCourse(john, math)
        
        assertEquals(emptySchool.courses, Nil())
        assertEquals(v1.courses, Cons("Math", Nil()))
        assertEquals(v1.setTeacherToCourse(john, italian).courses,
            Cons("Math", Cons("Italian", Nil())))
        
    @Test def testTeachers() =
        assertEquals(Nil(), emptySchool.teachers)
        assertEquals(Cons("John", Nil()), emptySchool.setTeacherToCourse(john, math).teachers)
        assertEquals(Cons("John", Nil()), emptySchool.setTeacherToCourse(john, math).setTeacherToCourse(john, italian).teachers)
        
        assertEquals(Cons("Albert", Cons("John", Nil())), emptySchool.setTeacherToCourse(albert, math).setTeacherToCourse(john, math).setTeacherToCourse(john, italian).teachers)
    
    @Test def testCoursesOfATeacher() =
        val johnMath = emptySchool.setTeacherToCourse(john, math)
        val johnMathItalian = johnMath.setTeacherToCourse(john, italian)
        
        assertEquals(Nil(), emptySchool.coursesOfATeacher(john))
        assertEquals(Cons("Math", Nil()), johnMath.coursesOfATeacher(john))
        assertEquals(Cons("Math", Cons("Italian", Nil())), johnMathItalian.coursesOfATeacher(john))
        
    @Test def testHasTeacher() =
        assertFalse(emptySchool.hasTeacher("John"))
        assertTrue(emptySchool.setTeacherToCourse(john, course("Math")).hasTeacher("John"))
        assertTrue(emptySchool.setTeacherToCourse(albert, math).setTeacherToCourse(john, math).hasTeacher("John"))