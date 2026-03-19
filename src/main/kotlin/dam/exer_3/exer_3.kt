package dam.exer_3

class Pipeline {

    // Passo 1:
    private val stages = mutableListOf<Pair<String, (List<String>) -> List<String>>>()

    fun addStage(name: String, transform: (List<String>) -> List<String>) {
        stages.add(Pair(name, transform))
    }


    fun execute(input: List<String>): List<String> {
        var currentData = input
        for (stage in stages) {
            currentData = stage.second(currentData)
        }
        return currentData
    }


    fun describe() {
        println("Pipeline stages:")
        stages.forEachIndexed { index, stage ->
            println("${index + 1}. ${stage.first}")
        }
    }

    // --- Challenge [4%] ---
    fun compose(name1: String, name2: String, newStageName: String) {
        val stage1 = stages.find { it.first == name1 }?.second
        val stage2 = stages.find { it.first == name2 }?.second

        if (stage1 != null && stage2 != null) {

            val composedTransform: (List<String>) -> List<String> = { input -> stage2(stage1(input)) }
            addStage(newStageName, composedTransform)
        }
    }

    fun fork(input: List<String>, pipeline1: Pipeline, pipeline2: Pipeline): Pair<List<String>, List<String>> {
        return Pair(pipeline1.execute(input), pipeline2.execute(input))
    }
}

// Passo 2:
fun buildPipeline(builderAction: Pipeline.() -> Unit): Pipeline {
    val pipeline = Pipeline()
    pipeline.builderAction()
    return pipeline
}

fun main() {
    val logs = listOf(
        "   INFO: server started   ",
        "ERROR: disk full",
        "  DEBUG: checking config ",
        " ERROR: out of memory",
        "INFO: request received",
        "ERROR: connection timeout  "
    )

    // Passo 3:
    val pipeline = buildPipeline {
        addStage("Trim") { list -> list.map { it.trim() } }
        addStage("Filter errors") { list -> list.filter { it.contains("ERROR") } }
        addStage("Uppercase") { list -> list.map { it.uppercase() } }
        addStage("Add index") { list -> list.mapIndexed { index, s -> "${index + 1}. $s" } }
    }

    // Passo 4:
    pipeline.describe()
    println("Result:")
    val result = pipeline.execute(logs)
    result.forEach { println(it) }
}