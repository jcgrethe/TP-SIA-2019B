function [res] = sigmoid_exp(x, beta)
    res = 1.0 ./ (1.0 .+ exp(-2 .* beta .* x));
end
