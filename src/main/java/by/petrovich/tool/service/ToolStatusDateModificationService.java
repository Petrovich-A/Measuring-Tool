package by.petrovich.tool.service;

import by.petrovich.tool.model.Tool;
import by.petrovich.tool.model.ToolStatusDateModification;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ToolStatusDateModificationService {
    /**
     * Creates a new ToolStatusDateModification entity based on the provided Tool and finish time.
     * <p>
     * This method constructs a new ToolStatusDateModification entity using the provided Tool and finish time (if available).
     * It then saves the entity to the database using the ToolStatusDateModificationRepository.
     * Finally, it returns the created ToolStatusDateModification entity.
     * <p>
     * The ToolStatusDateModification entity is saved when the status of the tool is changed, preserving the date and time.
     *
     * @param tool   The Tool entity associated with the ToolStatusDateModification.
     * @param finish The finish time for the ToolStatusDateModification, or null if not available.
     * @return The created ToolStatusDateModification entity.
     */
    @Transactional
    ToolStatusDateModification create(Tool tool, LocalDateTime finish);
}
