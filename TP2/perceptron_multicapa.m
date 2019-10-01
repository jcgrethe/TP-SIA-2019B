addpath("./activation_functions");

#####################################
# Neural net with incremental learn #
#####################################

#Starting parameters
L = 0.01;
MAX_ERROR = 0.01;
TRAINING_SET = [0, 0, 0; 1, 0, 0; 0, 1, 0; 1, 1, 1];
LEARN_ITERATIONS = 10000;
global_q_error = 1;
m = 0;
levels = 2;
w{1} = rand(2,3) - 0.5;
w{2} = rand(1,3) - 0.5;
#Training loop
while global_q_error > MAX_ERROR

	#Shuffle matrix
	r_training_set = TRAINING_SET(randperm(end),:);
	global_q_error = 0;

	for i = 1:rows(r_training_set)
		#Input vector
		in = [-1, r_training_set(i,:)];
		e = in(1:end - 1);

		h{1} = w{1} * e';
    v{1} = [-1 ,sigmoid_exp(h{1})']';
		for i = 2 : levels
      if i != levels
        	v{i} = [-1 ,sigmoid_exp(h{i})']';
      endif
			#Sum + nonlinear fn
			h{i} = w{i} * v{i-1};
			v{i} = sigmoid_exp(h{i});
		endfor
		
		#v{levels}
		#Backprop
		final_dif = (in(end:end) - v{levels});
		d{levels} = sigmoid_exp_d(h{levels}) * final_dif;
    
    #d{levels}
    
    for i=levels-1:-1:1
     
      #sigmoid_exp_d(h{i})
      #(w{i}(1:end, 2:end))'
      #d{i+1}
      
      d{i} = sigmoid_exp_d(h{i})' * ((w{i}(1:end, 2:end))' * d{i+1});
      #d{i}  
    endfor
    
		#Update w
		for i = 1 : levels
      if i == levels
        aux_v = v{i};
      else
        aux_v = v{i}(2:end);
      endif
      
      delta_w = L * (d{i} * aux_v);
			w{i} = w{i} + delta_w;
		endfor
		#ECM
		q_error = (1 / (2 * rows(r_training_set))) * (final_dif)**2;
		global_q_error += q_error;
	end

	xlabel ("Epoch");
	ylabel ("ECM");
	plot(m, global_q_error);
	hold on;
	m++;

	#printf("Epoch: %d\n", m);
end
