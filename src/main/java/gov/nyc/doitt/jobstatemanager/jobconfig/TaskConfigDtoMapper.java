package gov.nyc.doitt.jobstatemanager.jobconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * Map TaskConfig to and from TaskConfigDto
 * 
 */
@Component
public class TaskConfigDtoMapper {

	private ModelMapper modelMapper = new ModelMapper();

	private PropertyMap<TaskConfigDto, TaskConfig> taskConfigDtoPropertyMap = new PropertyMap<TaskConfigDto, TaskConfig>() {

		protected void configure() {
		}
	};

	public TaskConfigDtoMapper() {

		modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE);
		modelMapper.addMappings(taskConfigDtoPropertyMap);
	}

	public TaskConfig fromDto(TaskConfigDto taskConfigDto) {

		return modelMapper.map(taskConfigDto, TaskConfig.class);
	}

	public ArrayList<TaskConfig> fromDto(List<TaskConfigDto> taskConfigDtos) {

		if (CollectionUtils.isEmpty(taskConfigDtos))
			return new ArrayList<TaskConfig>();
		return taskConfigDtos.stream().map(p -> fromDto(p)).collect(Collectors.toCollection(ArrayList::new));

	}

	public TaskConfig fromDto(TaskConfigDto taskConfigDto, TaskConfig taskConfig) {

		modelMapper.map(taskConfigDto, taskConfig);
		return taskConfig;
	}

	public ArrayList<TaskConfigDto> toDto(List<TaskConfig> taskConfigs) {

		if (CollectionUtils.isEmpty(taskConfigs))
			return new ArrayList<TaskConfigDto>();
		return taskConfigs.stream().map(p -> toDto(p)).collect(Collectors.toCollection(ArrayList::new)); 
	}

	public TaskConfigDto toDto(TaskConfig taskConfig) {

		return modelMapper.map(taskConfig, TaskConfigDto.class);
	}

}
