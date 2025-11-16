package test.application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import obj.ArticleGroup;

/**
 * JUnit test class for ArticleGroup.
 * Tests all functionality of the ArticleGroup class including construction,
 * getters, setters, and article management operations.
 * 
 * @author Jad Khayyati
 * @version 1.0
 * @since 2025-11-16
 */
class ArticleGroupTest {
    
    private ArticleGroup testGroup;
    
    /**
     * Sets up test fixtures before each test method.
     * Creates a fresh ArticleGroup instance for testing.
     */
    @BeforeEach
    void setUp() {
        testGroup = new ArticleGroup(1, "Java Tutorials", "Collection of Java programming tutorials");
    }
    
    /**
     * Test 1: Verifies that a valid ArticleGroup can be created with proper attributes.
     */
    @Test
    void testValidGroupCreation() {
        assertNotNull(testGroup);
        assertEquals(1, testGroup.getGroupId());
        assertEquals("Java Tutorials", testGroup.getGroupName());
        assertEquals("Collection of Java programming tutorials", testGroup.getDescription());
        assertEquals(0, testGroup.getArticleCount());
    }
    
    /**
     * Test 2: Ensures that creating a group with null name throws IllegalArgumentException.
     */
    @Test
    void testNullGroupName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArticleGroup(1, null, "Valid description");
        });
    }
    
    /**
     * Test 3: Ensures that creating a group with empty name throws IllegalArgumentException.
     */
    @Test
    void testEmptyGroupName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArticleGroup(1, "", "Valid description");
        });
    }
    
    /**
     * Test 4: Ensures that creating a group with whitespace-only name throws IllegalArgumentException.
     */
    @Test
    void testWhitespaceGroupName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArticleGroup(1, "   ", "Valid description");
        });
    }
    
    /**
     * Test 5: Ensures that creating a group with null description throws IllegalArgumentException.
     */
    @Test
    void testNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArticleGroup(1, "Valid name", null);
        });
    }
    
    /**
     * Test 6: Ensures that creating a group with empty description throws IllegalArgumentException.
     */
    @Test
    void testEmptyDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ArticleGroup(1, "Valid name", "");
        });
    }
    
    /**
     * Test 7: Tests the setGroupName() method with a valid new name.
     */
    @Test
    void testSetValidGroupName() {
        testGroup.setGroupName("Python Tutorials");
        assertEquals("Python Tutorials", testGroup.getGroupName());
    }
    
    /**
     * Test 8: Ensures that setting an invalid name throws IllegalArgumentException.
     */
    @Test
    void testSetInvalidGroupName() {
        assertThrows(IllegalArgumentException.class, () -> {
            testGroup.setGroupName("");
        });
        // Verify original name is unchanged
        assertEquals("Java Tutorials", testGroup.getGroupName());
    }
    
    /**
     * Test 9: Tests the setDescription() method with a valid new description.
     */
    @Test
    void testSetValidDescription() {
        testGroup.setDescription("Updated description for tutorials");
        assertEquals("Updated description for tutorials", testGroup.getDescription());
    }
    
    /**
     * Test 10: Ensures that setting an invalid description throws IllegalArgumentException.
     */
    @Test
    void testSetInvalidDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            testGroup.setDescription(null);
        });
        // Verify original description is unchanged
        assertEquals("Collection of Java programming tutorials", testGroup.getDescription());
    }
    
    /**
     * Test 11: Tests adding a single article to the group.
     */
    @Test
    void testAddSingleArticle() {
        boolean result = testGroup.addArticle(101L);
        assertTrue(result);
        assertEquals(1, testGroup.getArticleCount());
        assertTrue(testGroup.containsArticle(101L));
    }
    
    /**
     * Test 12: Tests adding multiple articles to the group.
     */
    @Test
    void testAddMultipleArticles() {
        testGroup.addArticle(101L);
        testGroup.addArticle(102L);
        testGroup.addArticle(103L);
        
        assertEquals(3, testGroup.getArticleCount());
        assertTrue(testGroup.containsArticle(101L));
        assertTrue(testGroup.containsArticle(102L));
        assertTrue(testGroup.containsArticle(103L));
    }
    
    /**
     * Test 13: Tests that adding a duplicate article returns false and doesn't increase count.
     */
    @Test
    void testAddDuplicateArticle() {
        testGroup.addArticle(101L);
        boolean result = testGroup.addArticle(101L);
        
        assertFalse(result);
        assertEquals(1, testGroup.getArticleCount());
    }
    
    /**
     * Test 14: Tests removing an article from the group.
     */
    @Test
    void testRemoveArticle() {
        testGroup.addArticle(101L);
        testGroup.addArticle(102L);
        
        boolean result = testGroup.removeArticle(101L);
        
        assertTrue(result);
        assertEquals(1, testGroup.getArticleCount());
        assertFalse(testGroup.containsArticle(101L));
        assertTrue(testGroup.containsArticle(102L));
    }
    
    /**
     * Test 15: Tests that removing a non-existent article returns false.
     */
    @Test
    void testRemoveNonExistentArticle() {
        testGroup.addArticle(101L);
        boolean result = testGroup.removeArticle(999L);
        
        assertFalse(result);
        assertEquals(1, testGroup.getArticleCount());
    }
    
    /**
     * Test 16: Tests the containsArticle() method.
     */
    @Test
    void testContainsArticle() {
        assertFalse(testGroup.containsArticle(101L));
        
        testGroup.addArticle(101L);
        assertTrue(testGroup.containsArticle(101L));
        
        testGroup.removeArticle(101L);
        assertFalse(testGroup.containsArticle(101L));
    }
    
    /**
     * Test 17: Tests the getArticleIds() method returns a copy of the list.
     */
    @Test
    void testGetArticleIdsReturnsCopy() {
        testGroup.addArticle(101L);
        testGroup.addArticle(102L);
        
        var articleIds = testGroup.getArticleIds();
        articleIds.add(999L); // Modify the returned list
        
        // Original group should not be affected
        assertEquals(2, testGroup.getArticleCount());
        assertFalse(testGroup.containsArticle(999L));
    }
    
    /**
     * Test 18: Tests the clearArticles() method.
     */
    @Test
    void testClearArticles() {
        testGroup.addArticle(101L);
        testGroup.addArticle(102L);
        testGroup.addArticle(103L);
        
        testGroup.clearArticles();
        
        assertEquals(0, testGroup.getArticleCount());
        assertFalse(testGroup.containsArticle(101L));
    }
    
    /**
     * Test 19: Tests the toString() method produces expected output.
     */
    @Test
    void testToString() {
        testGroup.addArticle(101L);
        testGroup.addArticle(102L);
        
        String result = testGroup.toString();
        
        assertTrue(result.contains("groupId=1"));
        assertTrue(result.contains("groupName='Java Tutorials'"));
        assertTrue(result.contains("articleCount=2"));
    }
    
    /**
     * Test 20: Tests the equals() method for ArticleGroup comparison.
     */
    @Test
    void testEquals() {
        ArticleGroup group1 = new ArticleGroup(1, "Group 1", "Description 1");
        ArticleGroup group2 = new ArticleGroup(1, "Group 2", "Description 2");
        ArticleGroup group3 = new ArticleGroup(2, "Group 1", "Description 1");
        
        // Same ID means equal
        assertEquals(group1, group2);
        // Different ID means not equal
        assertNotEquals(group1, group3);
        // Same object
        assertEquals(group1, group1);
        // Null comparison
        assertNotEquals(group1, null);
    }
    
    /**
     * Test 21: Tests the hashCode() method for ArticleGroup.
     */
    @Test
    void testHashCode() {
        ArticleGroup group1 = new ArticleGroup(1, "Group 1", "Description 1");
        ArticleGroup group2 = new ArticleGroup(1, "Group 2", "Description 2");
        
        // Groups with same ID should have same hash code
        assertEquals(group1.hashCode(), group2.hashCode());
    }
    
    /**
     * Test 22: Tests that trimming works correctly for name and description.
     */
    @Test
    void testTrimming() {
        ArticleGroup group = new ArticleGroup(1, "  Trimmed Name  ", "  Trimmed Description  ");
        
        assertEquals("Trimmed Name", group.getGroupName());
        assertEquals("Trimmed Description", group.getDescription());
    }
}
