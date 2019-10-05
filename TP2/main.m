addpath("./activation_functions");

input_patterns = get_random_patterns("terrain02.data", 30);
data_set = load_terrain("terrain02.data");
plot_terrain(data_set);
disp('Terrain');
total_patterns = get_random_patterns("terrain02.data", 100);

w = incremental_trainer(input_patterns);
generated = predict(w, total_patterns);

plot_terrain(generated);
while(true)
endwhile
