addpath("./activation_functions");

[w, total_patterns] = incremental_trainer();
generated = predict(w);

figure(3);
p =plot_terrain(generated);

errort = total_patterns(:,3) - generated(:,3);
errorgraph = [generated(:,1), generated(:,2), errort(:,1)];

figure(10);
plot_terrain_2(generated, total_patterns(:,3));

figure(4);
h = plot_terrain(errorgraph);
waitfor(h)