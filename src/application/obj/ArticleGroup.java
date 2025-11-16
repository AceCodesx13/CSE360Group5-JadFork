package obj;

import java.util.ArrayList;
import java.util.List;

/**
 * The ArticleGroup class represents a group of help articles organized by topic.
 * Staff members can use groups to categorize and manage help content efficiently.
 * 
 * <p>This class provides functionality to:</p>
 * <ul>
 *   <li>Create article groups with names and descriptions</li>
 *   <li>Add and remove articles from groups</li>
 *   <li>Update group information</li>
 *   <li>Track articles within each group</li>
 * </ul>
 * 
 * @author Jad Khayyati
 * @version 1.0
 * @since 2025-11-16
 */
public class ArticleGroup {
    
    /** Unique identifier for this article group */
    private int groupId;
    
    /** Name of the article group */
    private String groupName;
    
    /** Description of what this group contains */
    private String description;
    
    /** List of article IDs that belong to this group */
    private List<Long> articleIds;
    
    /**
     * Constructs an ArticleGroup with the specified details.
     * 
     * @param groupId The unique identifier for this group
     * @param groupName The name of the article group
     * @param description A description of what this group contains
     * @throws IllegalArgumentException if groupName is null or empty
     * @throws IllegalArgumentException if description is null or empty
     */
    public ArticleGroup(int groupId, String groupName, String description) {
        if (groupName == null || groupName.trim().isEmpty()) {
            throw new IllegalArgumentException("Group name cannot be null or empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        
        this.groupId = groupId;
        this.groupName = groupName.trim();
        this.description = description.trim();
        this.articleIds = new ArrayList<>();
    }
    /**
     * Gets the unique identifier of this group.
     * 
     * @return The group ID
     */
    public int getGroupId() {
        return groupId;
    }
    
    /**
     * Gets the name of this article group.
     * 
     * @return The group name
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * Sets the name of this article group.
     * 
     * @param groupName The new group name
     * @throws IllegalArgumentException if groupName is null or empty
     */
    public void setGroupName(String groupName) {
        if (groupName == null || groupName.trim().isEmpty()) {
            throw new IllegalArgumentException("Group name cannot be null or empty");
        }
        this.groupName = groupName.trim();
    }
    
    /**
     * Gets the description of this article group.
     * 
     * @return The group description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description of this article group.
     * 
     * @param description The new description
     * @throws IllegalArgumentException if description is null or empty
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        this.description = description.trim();
    }
    
    /**
     * Gets the list of article IDs in this group.
     * Returns a copy to prevent external modification.
     * 
     * @return A copy of the list of article IDs
     */
    public List<Long> getArticleIds() {
        return new ArrayList<>(articleIds);
    }
    
    /**
     * Adds an article to this group.
     * 
     * @param articleId The ID of the article to add
     * @return true if the article was added, false if it was already in the group
     */
    public boolean addArticle(long articleId) {
        if (articleIds.contains(articleId)) {
            return false;
        }
        articleIds.add(articleId);
        return true;
    }
    
    /**
     * Removes an article from this group.
     * 
     * @param articleId The ID of the article to remove
     * @return true if the article was removed, false if it wasn't in the group
     */
    public boolean removeArticle(long articleId) {
        return articleIds.remove(Long.valueOf(articleId));
    }
    
    /**
     * Checks if this group contains a specific article.
     * 
     * @param articleId The ID of the article to check
     * @return true if the article is in this group, false otherwise
     */
    public boolean containsArticle(long articleId) {
        return articleIds.contains(articleId);
    }
    
    /**
     * Gets the number of articles in this group.
     * 
     * @return The count of articles in this group
     */
    public int getArticleCount() {
        return articleIds.size();
    }
    
    /**
     * Clears all articles from this group.
     * The group itself remains but becomes empty.
     */
    public void clearArticles() {
        articleIds.clear();
    }
    
    /**
     * Returns a string representation of this ArticleGroup.
     * 
     * @return A string containing the group's ID, name, description, and article count
     */
    @Override
    public String toString() {
        return "ArticleGroup{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", articleCount=" + articleIds.size() +
                '}';
    }
    
    /**
     * Checks if this ArticleGroup is equal to another object.
     * Two ArticleGroups are equal if they have the same groupId.
     * 
     * @param obj The object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ArticleGroup that = (ArticleGroup) obj;
        return groupId == that.groupId;
    }
    
    /**
     * Returns a hash code for this ArticleGroup.
     * 
     * @return The hash code based on the groupId
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(groupId);
    }
}
