rootProject.name = "java-courses"

val min = extra["minTask"]?.toString()?.toInt() ?: 1
val max = extra["maxTask"]?.toString()?.toInt() ?: 100
include((min..max).map { "tasks:task$it" })