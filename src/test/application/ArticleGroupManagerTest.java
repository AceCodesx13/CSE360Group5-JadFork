package test.application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import application.ArticleGroupManager;
import obj.ArticleGroup;
import java.util.List;
import java.util.Map;

/**
 * JUnit test class for ArticleGroupManager.
 * Tests all CRUD operations and management functionality for article groups.
 * 
 * @author Jad Khayyati
 * @version 1.0
 * @since 2025-11-16
 */
class ArticleGroupManagerTest {
    
    private ArticleGroupManager manager;
    
    /**
     * Sets up test fixtures before each test method.
     * Creates a fresh ArticleGroupManager instance for testing.
     */
    @BeforeEach
    void setUp() {
        manager = new ArticleGroupManager();
    }
    
    /**
     * Test 1: Verifies that a group can be created successfully.
     */
    @Test
    void testCreateGroup() {
        ArticleGroup group = manager.createGroup("Java Basics", "Basic Java concepts");
        
        assertNotNull(group);
        assertEquals("Java Basics", group.getGroupName());
        assertEquals("Basic Java concepts", group.getDescription());
        assertEquals(1, manager.getGroupCount());
    }
    
    /**
     * Test 2: Verifies that multiple groups can be created with unique IDs.
     */
    @Test
    void testCreateMultipleGroups() {
        ArticleGroup group1 = manager.createGroup("Group 1", "Description 1");
        ArticleGroup group2 = manager.createGroup("Group 2", "Description 2");
        ArticleGroup group3 = manager.createGroup("Group 3", "Description 3");
        
        assertNotEquals(group1.getGroupId(), group2.getGroupId());
        assertNotEquals(group2.getGroupId(), group3.getGroupId());
        assertEquals(3, manager.getGroupCount());
    }
    
    /**
     * Test 3: Verifies that creating a group with invalid name throws exception.
     */
    @Test
    void testCreateGroupInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            manager.createGroup("", "Valid description");
        });
    }
    
    /**
     * Test 4: Verifies that a group can be retrieved by ID.
     */
    @Test
    void testGetGroup() {
        ArticleGroup created = manager.createGroup("Test Group", "Test Description");
        ArticleGroup retrieved = manager.getGroup(created.getGroupId());
        
        assertNotNull(retrieved);
        assertEquals(created.getGroupId(), retrieved.getGroupId());
        assertEquals("Test Group", retrieved.getGroupName());
    }
    
    /**
     * Test 5: Verifies that getting a non-existent group returns null.
     */
    @Test
    void testGetNonExistentGroup() {
        ArticleGroup group = manager.getGroup(999);
        assertNull(group);
    }
    
    /**
     * Test 6: Verifies that all groups can be retrieved.
     */
    @Test
    void testGetAllGroups() {
        manager.createGroup("Group 1", "Description 1");
        manager.createGroup("Group 2", "Description 2");
        manager.createGroup("Group 3", "Description 3");
        
        List<ArticleGroup> allGroups = manager.getAllGroups();
        
        assertEquals(3, allGroups.size());
    }
    
    /**
     * Test 7: Verifies that getAllGroups returns empty list when no groups exist.
     */
    @Test
    void testGetAllGroupsEmpty() {
        List<ArticleGroup> allGroups = manager.getAllGroups();
        
        assertNotNull(allGroups);
        assertEquals(0, allGroups.size());
    }
    
    /**
     * Test 8: Tests updating a group's name.
     */
    @Test
    void testUpdateGroupName() {
        ArticleGroup group = manager.createGroup("Old Name", "Description");
        boolean result = manager.updateGroupName(group.getGroupId(), "New Name");
        
        assertTrue(result);
        assertEquals("New Name", group.getGroupName());
    }
    
    /**
     * Test 9: Tests that updating a non-existent group's name returns false.
     */
    @Test
    void testUpdateNonExistentGroupName() {
        boolean result = manager.updateGroupName(999, "New Name");
        assertFalse(result);
    }
    
    /**
     * Test 10: Tests updating a group's description.
     */
    @Test
    void testUpdateGroupDescription() {
        ArticleGroup group = manager.createGroup("Name", "Old Description");
        boolean result = manager.updateGroupDescription(group.getGroupId(), "New Description");
        
        assertTrue(result);
        assertEquals("New Description", group.getDescription());
    }
    
    /**
     * Test 11: Tests updating both name and description.
     */
    @Test
    void testUpdateGroup() {
        ArticleGroup group = manager.createGroup("Old Name", "Old Description");
        boolean result = manager.updateGroup(group.getGroupId(), "New Name", "New Description");
        
        assertTrue(result);
        assertEquals("New Name", group.getGroupName());
        assertEquals("New Description", group.getDescription());
    }
    
    /**
     * Test 12: Tests deleting a group.
     */
    @Test
    void testDeleteGroup() {
        ArticleGroup group = manager.createGroup("Test Group", "Test Description");
        int groupId = group.getGroupId();
        
        boolean result = manager.deleteGroup(groupId);
        
        assertTrue(result);
        assertNull(manager.getGroup(groupId));
        assertEquals(0, manager.getGroupCount());
    }
    
    /**
     * Test 13: Tests that deleting a non-existent group returns false.
     */
    @Test
    void testDeleteNonExistentGroup() {
        boolean result = manager.deleteGroup(999);
        assertFalse(result);
    }
    
    /**
     * Test 14: Tests adding an article to a group.
     */
    @Test
    void testAddArticleToGroup() {
        ArticleGroup group = manager.createGroup("Test Group", "Test Description");
        boolean result = manager.addArticleToGroup(group.getGroupId(), 101L);
        
        assertTrue(result);
        assertTrue(group.containsArticle(101L));
        assertEquals(1, group.getArticleCount());
    }
    
    /**
     * Test 15: Tests adding multiple articles to a group.
     */
    @Test
    void testAddMultipleArticlesToGroup() {
        ArticleGroup group = manager.createGroup("Test Group", "Test Description");
        
        manager.addArticleToGroup(group.getGroupId(), 101L);
        manager.addArticleToGroup(group.getGroupId(), 102L);
        manager.addArticleToGroup(group.getGroupId(), 103L);
        
        assertEquals(3, group.getArticleCount());
    }
    
    /**
     * Test 16: Tests that adding an article to a non-existent group returns false.
     */
    @Test
    void testAddArticleToNonExistentGroup() {
        boolean result = manager.addArticleToGroup(999, 101L);
        assertFalse(result);
    }
    
    /**
     * Test 17: Tests that adding a duplicate article returns false.
     */
    @Test
    void testAddDuplicateArticle() {
        ArticleGroup group = manager.createGroup("Test Group", "Test Description");
        
        manager.addArticleToGroup(group.getGroupId(), 101L);
        boolean result = manager.addArticleToGroup(group.getGroupId(), 101L);
        
        assertFalse(result);
        assertEquals(1, group.getArticleCount());
    }
    
    /**
     * Test 18: Tests removing an article from a group.
     */
    @Test
    void testRemoveArticleFromGroup() {
        ArticleGroup group = manager.createGroup("Test Group", "Test Description");
        manager.addArticleToGroup(group.getGroupId(), 101L);
        
        boolean result = manager.removeArticleFromGroup(group.getGroupId(), 101L);
        
        assertTrue(result);
        assertFalse(group.containsArticle(101L));
        assertEquals(0, group.getArticleCount());
    }
    
    /**
     * Test 19: Tests that removing from a non-existent group returns false.
     */
    @Test
    void testRemoveArticleFromNonExistentGroup() {
        boolean result = manager.removeArticleFromGroup(999, 101L);
        assertFalse(result);
    }
    
    /**
     * Test 20: Tests checking if a group contains an article.
     */
    @Test
    void testGroupContainsArticle() {
        ArticleGroup group = manager.createGroup("Test Group", "Test Description");
        manager.addArticleToGroup(group.getGroupId(), 101L);
        
        assertTrue(manager.groupContainsArticle(group.getGroupId(), 101L));
        assertFalse(manager.groupContainsArticle(group.getGroupId(), 999L));
    }
    
    /**
     * Test 21: Tests getting all groups that contain a specific article.
     */
    @Test
    void testGetGroupsContainingArticle() {
        ArticleGroup group1 = manager.createGroup("Group 1", "Description 1");
        ArticleGroup group2 = manager.createGroup("Group 2", "Description 2");
        ArticleGroup group3 = manager.createGroup("Group 3", "Description 3");
        
        manager.addArticleToGroup(group1.getGroupId(), 101L);
        manager.addArticleToGroup(group2.getGroupId(), 101L);
        manager.addArticleToGroup(group3.getGroupId(), 999L);
        
        List<ArticleGroup> groups = manager.getGroupsContainingArticle(101L);
        
        assertEquals(2, groups.size());
        assertTrue(groups.contains(group1));
        assertTrue(groups.contains(group2));
        assertFalse(groups.contains(group3));
    }
    
    /**
     * Test 22: Tests searching groups by name.
     */
    @Test
    void testSearchGroupsByName() {
        manager.createGroup("Java Basics", "Basic Java");
        manager.createGroup("Java Advanced", "Advanced Java");
        manager.createGroup("Python Basics", "Basic Python");
        
        List<ArticleGroup> results = manager.searchGroupsByName("Java");
        
        assertEquals(2, results.size());
    }
    
    /**
     * Test 23: Tests case-insensitive search.
     */
    @Test
    void testSearchGroupsCaseInsensitive() {
        manager.createGroup("Java Basics", "Description");
        
        List<ArticleGroup> results = manager.searchGroupsByName("java");
        
        assertEquals(1, results.size());
    }
    
    /**
     * Test 24: Tests that searching with empty string returns empty list.
     */
    @Test
    void testSearchGroupsEmptyTerm() {
        manager.createGroup("Test Group", "Description");
        
        List<ArticleGroup> results = manager.searchGroupsByName("");
        
        assertEquals(0, results.size());
    }
    
    /**
     * Test 25: Tests the groupExists method.
     */
    @Test
    void testGroupExists() {
        ArticleGroup group = manager.createGroup("Test Group", "Description");
        
        assertTrue(manager.groupExists(group.getGroupId()));
        assertFalse(manager.groupExists(999));
    }
    
    /**
     * Test 26: Tests clearing all groups.
     */
    @Test
    void testClearAllGroups() {
        manager.createGroup("Group 1", "Description 1");
        manager.createGroup("Group 2", "Description 2");
        manager.createGroup("Group 3", "Description 3");
        
        manager.clearAllGroups();
        
        assertEquals(0, manager.getGroupCount());
        assertEquals(0, manager.getAllGroups().size());
    }
    
    /**
     * Test 27: Tests that IDs reset after clearing all groups.
     */
    @Test
    void testIDsResetAfterClear() {
        manager.createGroup("Group 1", "Description 1");
        manager.createGroup("Group 2", "Description 2");
        manager.clearAllGroups();
        
        ArticleGroup newGroup = manager.createGroup("New Group", "New Description");
        
        assertEquals(1, newGroup.getGroupId());
    }
    
    /**
     * Test 28: Tests getting statistics.
     */
    @Test
    void testGetStatistics() {
        ArticleGroup group1 = manager.createGroup("Group 1", "Description 1");
        ArticleGroup group2 = manager.createGroup("Group 2", "Description 2");
        ArticleGroup group3 = manager.createGroup("Group 3", "Description 3");
        
        manager.addArticleToGroup(group1.getGroupId(), 101L);
        manager.addArticleToGroup(group1.getGroupId(), 102L);
        manager.addArticleToGroup(group2.getGroupId(), 103L);
        // group3 has no articles
        
        Map<String, Integer> stats = manager.getStatistics();
        
        assertEquals(3, stats.get("totalGroups"));
        assertEquals(3, stats.get("totalArticles"));
        assertEquals(2, stats.get("maxArticlesInGroup"));
        assertEquals(1, stats.get("emptyGroups"));
    }
    
    /**
     * Test 29: Tests statistics with empty manager.
     */
    @Test
    void testGetStatisticsEmpty() {
        Map<String, Integer> stats = manager.getStatistics();
        
        assertEquals(0, stats.get("totalGroups"));
        assertEquals(0, stats.get("totalArticles"));
        assertEquals(0, stats.get("maxArticlesInGroup"));
        assertEquals(0, stats.get("emptyGroups"));
    }
    
    /**
     * Test 30: Integration test - complete CRUD cycle.
     */
    @Test
    void testCompleteCRUDCycle() {
        // Create
        ArticleGroup group = manager.createGroup("Test Group", "Test Description");
        assertNotNull(group);
        
        // Add articles
        manager.addArticleToGroup(group.getGroupId(), 101L);
        manager.addArticleToGroup(group.getGroupId(), 102L);
        
        // Read
        ArticleGroup retrieved = manager.getGroup(group.getGroupId());
        assertEquals(2, retrieved.getArticleCount());
        
        // Update
        manager.updateGroup(group.getGroupId(), "Updated Name", "Updated Description");
        assertEquals("Updated Name", retrieved.getGroupName());
        
        // Delete article
        manager.removeArticleFromGroup(group.getGroupId(), 101L);
        assertEquals(1, retrieved.getArticleCount());
        
        // Delete group
        manager.deleteGroup(group.getGroupId());
        assertNull(manager.getGroup(group.getGroupId()));
    }
}
