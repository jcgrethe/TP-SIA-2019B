function [res] = sigmoid_exp_d(x)
    res = 2 .* x .* (1 - x);
end
