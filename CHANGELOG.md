# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.4.0] - 2023-11-28
### Added
- Implement retrieve projects belong to a specific user.
- Implement retrieve all template projects from cooperative.
- Implement clone project for worker.

### Changed
- Subtasks are now a separated entity.

## [0.3.0] - 2023-11-21
### Added
- Add user mock service.
- Implement task date calculation.

### Changed
- Enhance partial update.

## [0.2.0] - 2023-11-18
### Added
- Task will have a resource record automatically added and linked to it during creation.
- Resource and material entity relation.
- Added start date and end date for task entity.
- Added duration for project entity.
- Added task deletion mechanism to remove all subtasks when deleted.
- Added project deletion mechanism to remove all tasks belong to it when deleted.
- Added status for task entity.
- Added status for project entity.

### Changed
- Update API for entities from PUT to PATCH.
- Material type is now a separated entity.

### Fixed
- Update API no longer required id attribute in request body.

## [0.1.0] - 2023-10-31
### Added
- Added project entity.
- Added task entity.
- Added material entity.
- Added project and task relation.
- Added subtask mechanism.

## [0.0.1] - 2023-10-22
- Initial setup.