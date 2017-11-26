import org.hobbit.core.components.AbstractBenchmarkController;

/**
 * Created by DANISH on 11/26/2017.
 */
public class FactBenchBenchmarkController extends AbstractBenchmarkController {

    @Override
    protected void executeBenchmark() throws Exception {
        /*
        * FactBench would be working as a docker container
        * and we would have to set env here according to the port on which FactBench will be live
        * */

        /*
        * FactBench container would be taskGenerator's docker image
        * */

        /*
        * Also have to create evaluation storage image
        * */

        triggerDataGenerator();
        triggerTaskGenerator();
        createEvaluationStorageImage();
    }

    protected void triggerDataGenerator() {
        String dataGeneratorImg = "";
        int numDataGenerator = 1;
        String[] env = new String[]{};      // Docker port, seedId etc.
        createTaskGenerators(dataGeneratorImg, numDataGenerator, env);
    }

    protected void triggerTaskGenerator() {
        String taskGeneratorImg = "";
        int numTaskGenerator = 1;
        String[] env = new String[]{};      // Docker port, seedId etc.
        createTaskGenerators(taskGeneratorImg, numTaskGenerator, env);
    }

    public void createEvaluationStorageImage() {
        String evaluationStorageImg = "";
        int numTaskGenerator = 1;
        String[] env = new String[]{"ACKNOWLEDGEMENT_FLAG=true"};      // Docker port, seedId etc.
        createEvaluationStorage(DEFAULT_EVAL_STORAGE_IMAGE, env);
    }
}
