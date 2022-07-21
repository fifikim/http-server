require "./features/steps/shared.rb"

class Spinach::Features::HealthCheck < Spinach::FeatureSteps
  include Shared::Standard

  step "I make a GET request to '/health-check.html'" do
    @response = Requests.get("/health-check.html", { "Accept": "text/html" })
  end

  step 'the body should say that the status is passing' do
    expect(@response.body).to include("<strong>Status:</strong> pass")
  end
end
