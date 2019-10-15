
hidden_layers = [50 50 50 50];
# Data
total_patterns = load_terrain("terrain02.data");

debug_on_interrupt(false);

w0 = [];
% w0 = [1.13780,-0.88420,1.05530;
% 0.94810,-0.88420,0.95980;
% -0.94120,0.86990,0.95340;
% 0.94810,-1.30240,0.93130;
% -1.13840,1.40130,0.90670;
% -1.36640,0.86990,0.90250;
% -0.59230,0.86990,0.90040;
% -1.13840,1.24640,0.88890;
% -1.13840,0.86990,0.88780;
% 1.13780,-1.39620,0.88750;
% -0.94120,1.24640,0.88240;
% -0.94120,0.55110,0.88120];
LEARNING_PERCENTAGE = 40;

# Function
index = 2;
functions = {@hyp_tan, @sigmoid_exp};
derived_functions = {@hyp_tan_d, @sigmoid_exp_d};
inverses_functions = {@hyp_tan_inv, @sigmoid_exp_inv};
functions_min = {-0.93, 0.05};
functions_max = {0.93, 0.95};
func = functions{index};
func_d = derived_functions{index};
func_inv = inverses_functions{index};
f_min = functions_min{index};
f_max = functions_max{index};
mins = min(total_patterns);
maxs = max(total_patterns);
beta = 1.5;


# Normalization
for i = 1:columns(total_patterns) - 1
    total_patterns(:, i) = normalize(total_patterns(:, i), [func_inv(f_min), func_inv(f_max)], mins(i), maxs(i));
endfor    
total_patterns(:, 3) = normalize(total_patterns(:, 3), [f_min, f_max], mins(3), maxs(3));

if(size(w0) != 0)
    # Normalization
    for i = 1:columns(w0) - 1
        w0(:, i) = normalize(w0(:, i), [func_inv(f_min), func_inv(f_max)], mins(i), maxs(i));
    endfor    
    w0(:, 3) = normalize(w0(:, 3), [f_min, f_max], mins(3), maxs(3));
endif

epoch = 0;

# ETAs config values
adaptative_eta = true;
eta = 3;
eta_a = 0.0001;
eta_b = 0.05;
eta_min = 0.0001;
eta_max = 15;

# Momentum
momentum = false;
momentum_value = 0.9;

# Error config
MAX_ERROR = 0.00025;
total_error = 1;
last_error = 0;
MAX_EPOCH = 30000;

# Random seed set
rand('state',1);

