
global hidden_layers = [20 20 20 3];
# Data
global total_patterns = load_terrain("terrain02.data");

w0 = [];
LEARNING_PERCENTAGE = 60;
if(size(w0) != 0)
    total_patterns = [total_patterns; w0];
endif


# Function
index = 1;
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
eta = 0.01;
eta_a = 0.0001;
eta_b = 0.05;
eta_min = 0.001;
eta_max = 0.015;

# Momentum
momentum = false;
momentum_value = 0.4;

# Error config
MAX_ERROR = 0.0001;
total_error = 1;
last_error = 99999999;
MAX_EPOCH = 3000;