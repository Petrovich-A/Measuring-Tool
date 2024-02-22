package by.petrovich.tool.service.impl;

import by.petrovich.tool.model.Tool;
import by.petrovich.tool.model.ToolStatusDateModification;
import by.petrovich.tool.repository.ToolStatusDateModificationRepository;
import by.petrovich.tool.service.ToolStatusDateModificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ToolStatusDateModificationServiceImpl implements ToolStatusDateModificationService {
    private final ToolStatusDateModificationRepository toolStatusDateModificationRepository;

    @Override
    @Transactional
    public ToolStatusDateModification create(Tool tool, LocalDateTime finish) {
        return toolStatusDateModificationRepository.save(buildToolStatusDateModification(tool, finish));
    }

    private ToolStatusDateModification buildToolStatusDateModification(Tool tool, LocalDateTime finish) {
        return ToolStatusDateModification.builder()
                .toolStatus(tool.getToolStatus())
                .tool(tool)
                .finish(finish)
                .build();
    }
}
