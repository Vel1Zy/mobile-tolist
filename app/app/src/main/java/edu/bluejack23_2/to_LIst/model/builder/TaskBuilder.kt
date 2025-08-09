package edu.bluejack23_2.to_LIst.model.builder

import edu.bluejack23_2.to_LIst.model.models.Task
import edu.bluejack23_2.to_LIst.ui.interfaces.TaskCreate
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import java.util.UUID

object TaskBuilder {
    fun createTask(taskCreate: TaskCreate): Task {
        return Task(
            UUID.randomUUID().toString(),
            taskCreate.title,
            taskCreate.description,
            taskCreate.category,
            taskCreate.createdBy,
            taskCreate.deadline
        )
    }
}