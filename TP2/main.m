addpath("./activation_functions");

input_patterns = get_random_patterns("terrain02.data", 60);
data_set = load_terrain("terrain02.data");
figure(1);
plot_terrain(data_set);
disp('Terrain');
total_patterns = get_random_patterns("terrain02.data", 100);
w = incremental_trainer(input_patterns);
generated = predict(w, total_patterns);
figure(3);
p =plot_terrain(generated);
figure(4);
errort = total_patterns(:,3) - generated(:,3);
errorgraph = [ generated(:,1),generated(:,2),errort(:,1) ];
plot_terrain(errorgraph);
while(true)
endwhile