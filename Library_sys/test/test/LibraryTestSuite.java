package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    LibraryTest.class,
    BookTest.class,
    UserTest.class
})
public class LibraryTestSuite {
    // The class remains empty, 
    // being used only as a holder for the above annotations
} 