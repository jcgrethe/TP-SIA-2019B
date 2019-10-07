addpath("./activation_functions");
[w, total_patterns] = incremental_trainer();
generated = predict(w);
figure(3);
p =plot_terrain(generated);
figure(4);
errort = total_patterns(:,3) - generated(:,3);
errorgraph = [generated(:,1), generated(:,2), errort(:,1)];
plot_terrain(errorgraph);
while(true)
endwhile