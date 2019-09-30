addpath("./activation_functions");

#####################################
# Neural net with incremental learn #
#####################################

#Starting parameters
L = 0.05;
MAX_ERROR = 0.01;
w = rand(1,3) - 0.5;
TRAINING_SET = [0, 0, 0; 1, 0, 1; 0, 1, 1; 1, 1, 0];
LEARN_ITERATIONS = 10000;
global_q_error = 1;
m = 0;


#Training loop
while global_q_error > MAX_ERROR

	#Shuffle matrix
	r_training_set = TRAINING_SET(randperm(end),:);
	global_q_error = 0;

	for i = 1:rows(r_training_set)
		#Input vector
		in = [-1, r_training_set(i,:)];
		e = in(1:end - 1);

		#Sum + nonlinear fn
		h = [w * e'];
		o = sigmoid_exp(h);

		#Backprop
		dif = (in(end:end) - o);
		d = sigmoid_exp_d(o) * dif;

		#Update w
		delta_w = L * d * e;
		w = w + delta_w;
		
		#ECM
		q_error = (1 / (2 * rows(r_training_set))) * (dif)**2;
		global_q_error += q_error;
	end

	xlabel ("Epoch");
	ylabel ("ECM");
	plot(m, global_q_error);
	hold on;
	m++;

	printf("Epoch: %d\n", m);
end
