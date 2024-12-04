/**
 * 
 */
/**
 * 
 */
module Library_Management_System {
    requires junit;
    requires java.base;
    
    exports project;
    exports test;
    opens test to junit;
}