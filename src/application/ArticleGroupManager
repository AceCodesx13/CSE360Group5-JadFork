package application;

import obj.ArticleGroup;
import java.util.*;

/**
 * The ArticleGroupManager class manages all operations related to ArticleGroups.
 * This includes creating, reading, updating, and deleting article groups, as well
 * as managing the articles within each group.
 * 
 * <p>This manager provides CRUD operations for Staff members to organize help articles:</p>
 * <ul>
 *   <li>Create new article groups</li>
 *   <li>Retrieve existing groups</li>
 *   <li>Update group information</li>
 *   <li>Delete groups</li>
 *   <li>Manage articles within groups</li>
 * </ul>
 * 
 * @author Jad Khayyati
 * @version 1.0
 * @since 2025-11-16
 */
public class ArticleGroupManager {
    
    /** Map storing all article groups, keyed by group ID */
    private Map<Integer, ArticleGroup> groups;
    
    /** Counter for generating unique group IDs */
    private int nextGroupId;
    
    /**
     * Constructs a new ArticleGroupManager with an empty collection of groups.
     */
    public ArticleGroupManager() {
        this.groups = new HashMap<>();
        this.nextGroupId = 1;
    }
    
    /**
     * Creates a new article group with the specified name and description.
     * 
     * @param groupName The name for the new group
     * @param description The description for the new group
     * @return The newly created ArticleGroup
     * @throws IllegalArgumentException if groupName or description is invalid
     */
    public ArticleGroup createGroup(String groupName, String description) {
        ArticleGroup newGroup = new ArticleGroup(nextGroupId, groupName, description);
        groups.put(nextGroupId, newGroup);
        nextGroupId++;
        return newGroup;
    }
    
    /**
     * Retrieves an article group by its ID.
     * 
     * @param groupId The ID of the group to retrieve
     * @return The ArticleGroup with the specified ID, or null if not found
     */
    public ArticleGroup getGroup(int groupId) {
        return groups.get(groupId);
    }
    
    /**
     * Retrieves all article groups.
     * 
     * @return A list of all ArticleGroups in the system
     */
    public List<ArticleGroup> getAllGroups() {
        return new ArrayList<>(groups.values());
    }
    
    /**
     * Updates the name of an existing article group.
     * 
     * @param groupId The ID of the group to update
     * @param newName The new name for the group
     * @return true if the group was updated successfully, false if group not found
     * @throws IllegalArgumentException if newName is invalid
     */
    public boolean updateGroupName(int groupId, String newName) {
        ArticleGroup group = groups.get(groupId);
        if (group == null) {
            return false;
        }
        group.setGroupName(newName);
        return true;
    }
    
    /**
     * Updates the description of an existing article group.
     * 
     * @param groupId The ID of the group to update
     * @param newDescription The new description for the group
     * @return true if the group was updated successfully, false if group not found
     * @throws IllegalArgumentException if newDescription is invalid
     */
    public boolean updateGroupDescription(int groupId, String newDescription) {
        ArticleGroup group = groups.get(groupId);
        if (group == null) {
            return false;
        }
        group.setDescription(newDescription);
        return true;
    }
    
    /**
     * Updates both the name and description of an existing article group.
     * 
     * @param groupId The ID of the group to update
     * @param newName The new name for the group
     * @param newDescription The new description for the group
     * @return true if the group was updated successfully, false if group not found
     * @throws IllegalArgumentException if newName or newDescription is invalid
     */
    public boolean updateGroup(int groupId, String newName, String newDescription) {
        ArticleGroup group = groups.get(groupId);
        if (group == null) {
            return false;
        }
        group.setGroupName(newName);
        group.setDescription(newDescription);
        return true;
    }
    
    /**
     * Deletes an article group by its ID.
     * 
     * @param groupId The ID of the group to delete
     * @return true if the group was deleted successfully, false if group not found
     */
    public boolean deleteGroup(int groupId) {
        return groups.remove(groupId) != null;
    }
    
    /**
     * Adds an article to a specific group.
     * 
     * @param groupId The ID of the group to add the article to
     * @param articleId The ID of the article to add
     * @return true if the article was added successfully, false if group not found or article already in group
     */
    public boolean addArticleToGroup(int groupId, long articleId) {
        ArticleGroup group = groups.get(groupId);
        if (group == null) {
            return false;
        }
        return group.addArticle(articleId);
    }
    
    /**
     * Removes an article from a specific group.
     * 
     * @param groupId The ID of the group to remove the article from
     * @param articleId The ID of the article to remove
     * @return true if the article was removed successfully, false if group not found or article not in group
     */
    public boolean removeArticleFromGroup(int groupId, long articleId) {
        ArticleGroup group = groups.get(groupId);
        if (group == null) {
            return false;
        }
        return group.removeArticle(articleId);
    }
    
    /**
     * Checks if a group contains a specific article.
     * 
     * @param groupId The ID of the group to check
     * @param articleId The ID of the article to check for
     * @return true if the group contains the article, false otherwise
     */
    public boolean groupContainsArticle(int groupId, long articleId) {
        ArticleGroup group = groups.get(groupId);
        if (group == null) {
            return false;
        }
        return group.containsArticle(articleId);
    }
    
    /**
     * Gets all groups that contain a specific article.
     * 
     * @param articleId The ID of the article to search for
     * @return A list of ArticleGroups that contain the specified article
     */
    public List<ArticleGroup> getGroupsContainingArticle(long articleId) {
        List<ArticleGroup> result = new ArrayList<>();
        for (ArticleGroup group : groups.values()) {
            if (group.containsArticle(articleId)) {
                result.add(group);
            }
        }
        return result;
    }
    
    /**
     * Searches for article groups by name (case-insensitive partial match).
     * 
     * @param searchTerm The term to search for in group names
     * @return A list of ArticleGroups whose names contain the search term
     */
    public List<ArticleGroup> searchGroupsByName(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String lowerSearchTerm = searchTerm.toLowerCase().trim();
        List<ArticleGroup> results = new ArrayList<>();
        
        for (ArticleGroup group : groups.values()) {
            if (group.getGroupName().toLowerCase().contains(lowerSearchTerm)) {
                results.add(group);
            }
        }
        
        return results;
    }
    
    /**
     * Gets the total number of article groups in the system.
     * 
     * @return The count of article groups
     */
    public int getGroupCount() {
        return groups.size();
    }
    
    /**
     * Checks if a group exists with the specified ID.
     * 
     * @param groupId The ID to check
     * @return true if a group with this ID exists, false otherwise
     */
    public boolean groupExists(int groupId) {
        return groups.containsKey(groupId);
    }
    
    /**
     * Clears all article groups from the system.
     * This operation cannot be undone.
     */
    public void clearAllGroups() {
        groups.clear();
        nextGroupId = 1;
    }
    
    /**
     * Gets statistics about the article groups in the system.
     * 
     * @return A map containing statistics (total groups, total articles, etc.)
     */
    public Map<String, Integer> getStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("totalGroups", groups.size());
        
        int totalArticles = 0;
        int maxArticlesInGroup = 0;
        int emptyGroups = 0;
        
        for (ArticleGroup group : groups.values()) {
            int count = group.getArticleCount();
            totalArticles += count;
            maxArticlesInGroup = Math.max(maxArticlesInGroup, count);
            if (count == 0) {
                emptyGroups++;
            }
        }
        
        stats.put("totalArticles", totalArticles);
        stats.put("maxArticlesInGroup", maxArticlesInGroup);
        stats.put("emptyGroups", emptyGroups);
        
        return stats;
    }
}
